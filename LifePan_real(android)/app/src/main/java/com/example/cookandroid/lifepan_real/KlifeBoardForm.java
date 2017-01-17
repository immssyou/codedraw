package com.example.cookandroid.lifepan_real;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cookandroid.lifepan_real.helper.PhotoHelper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by immss_000 on 2015-11-22.
 */
public class KlifeBoardForm extends AppCompatActivity {
    ImageView openGalleryBtn, backBtn;
    TextView klife_edit_txt;
    Bitmap bmp = null;
    ImageView uploadImageView, uploadBtn;
    JSONObject user;
    AsyncHttpClientLP client = new AsyncHttpClientLP();
    RequestParams params = new RequestParams();
    File myFile = new File("");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klife_board_form);

        openGalleryBtn = (ImageView) findViewById(R.id.openGalleryBtn);
        klife_edit_txt = (TextView) findViewById(R.id.klife_edit_txt);
        uploadImageView = (ImageView) findViewById(R.id.uploadImageView);
        uploadBtn = (ImageView) findViewById(R.id.uploadBtn);
        backBtn = (ImageView) findViewById(R.id.backBtn);

        try{
            user = new JSONObject(getIntent().getStringExtra("user"));
        } catch (Exception e){

        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        openGalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoHelper.getInstance().callGallery(KlifeBoardForm.this, 100);
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    params.put("userNo",user.getInt("userNo"));
                    params.put("content", klife_edit_txt.getText().toString());
                    params.put("uploadedFile", myFile);
                } catch (Exception e) {

                }
                client.post("insertKlifeBoard", params,  new JsonHttpResponseHandler() {

                    ProgressDialog dialog;

                    /** 통신 시작시에 실행된다. */
                    @Override
                    public void onStart() {
                        dialog = new ProgressDialog(KlifeBoardForm.this);
                        dialog.setMessage("잠시만 기다려주세요...");
                        dialog.setCancelable(false);
                        dialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            Toast.makeText(getApplicationContext(), "업로드를 완료하였습니다.", Toast.LENGTH_SHORT).show();
                        } catch (Exception e){

                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                    }
                    /** 성공,실패 여부에 상관 없이 통신이 종료되면 실행된다. */
                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                        dialog = null;
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("user", user.toString());
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 갤러리의 선택 결과(경로)를 받아서 썸네일 이미지로 변환 후,
        // 이미지 뷰에 출력한다.
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                String path = PhotoHelper.getInstance().getPhotoPath(this, data);

                uploadImageView.setImageBitmap(null);

                if (bmp != null) {
                    bmp.recycle();
                    bmp = null;
                }

                bmp = PhotoHelper.getInstance().getThumb(this, path);
                uploadImageView.setImageBitmap(bmp);

                // 업로드
                myFile = new File(path);


            }
        }
    }

}
