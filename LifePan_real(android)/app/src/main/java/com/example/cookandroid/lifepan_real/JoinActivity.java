package com.example.cookandroid.lifepan_real;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class JoinActivity extends AppCompatActivity {
    TextView backLoginView;
    EditText joinPasswordText, joinEmailText, joinIdNumberText, joinNameText;
    Button btnRegister;
    AsyncHttpClientLP client = new AsyncHttpClientLP();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        joinPasswordText = (EditText) findViewById(R.id.passwordText);
        joinEmailText = (EditText) findViewById(R.id.idText);
        joinIdNumberText = (EditText) findViewById(R.id.joinIdNumberText);
        joinNameText = (EditText) findViewById(R.id.joinNameText);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        backLoginView = (TextView) findViewById(R.id.backLoginView);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = joinEmailText.getText().toString();
                String pswd = joinPasswordText.getText().toString();
                String studentNo = joinIdNumberText.getText().toString();
                String name = joinNameText.getText().toString();
                /*회원가입 양식에대한 유효성검사*/
                if(!(userId.trim().length() >0)){
                    Toast.makeText(getApplicationContext(), "ID 정보가 비어있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(pswd.trim().length() >0)){
                    Toast.makeText(getApplicationContext(), "패스워드 정보가 비어있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(studentNo.trim().length() >0)){
                    Toast.makeText(getApplicationContext(), "학번 정보가 비어있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(name.trim().length() >0)){
                    Toast.makeText(getApplicationContext(), "이름 정보가 비어있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("pswd", pswd);
                params.put("studentNo", Integer.parseInt(studentNo));
                params.put("name",name);
                client.post("insertUser", params,  new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            String result = response.getString("result");
                            if(result.equals("success")){
                                Intent mainIntent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(mainIntent);
                                Toast.makeText(getApplicationContext(), "회원가입을 완료하였습니다.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "이미 등록된 ID 입니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                    }
                });
            }
        });
        backLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
