package com.example.cookandroid.lifepan_real;

import android.app.ActionBar;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class LoginActivity extends AppCompatActivity {
    EditText idText, passwordText;
    Button btnLogin;
    TextView joinTextView;
    private BackPressCloseHandler backPressCloseHandler;
    AsyncHttpClientLP client = new AsyncHttpClientLP();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backPressCloseHandler = new BackPressCloseHandler(this);
        idText = (EditText) findViewById(R.id.idText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        joinTextView = (TextView) findViewById(R.id.joinTextView);


        joinTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams();
                params.put("userId", idText.getText().toString());
                params.put("pswd", passwordText.getText().toString());

                JSONObject user = new JSONObject();

                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                mainIntent.putExtra("user", user.toString());
                startActivity(mainIntent);

         /*       client.post("doLogin", params,  new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            JSONObject user = response.getJSONObject("user");
                            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                            mainIntent.putExtra("user",user.toString());
                            startActivity(mainIntent);
                        } catch (Exception e){
                            Toast.makeText(getApplicationContext(),"로그인 정보가 틀립니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });*/
            }
        });

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }
}
