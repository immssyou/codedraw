package com.example.cookandroid.lifepan_real;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by immss_000 on 2015-11-26.
 */
public class RealChatFragment extends Fragment {

    WebSocketClient mWebSocketClient;
    Button sendMessageBtn;
    JSONObject user;
    public static RealChatFragment newInstance() {
        RealChatFragment fragment = new RealChatFragment();
        return fragment;
    }

    public RealChatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_real_chat, container, false);
        sendMessageBtn = (Button) view.findViewById(R.id.sendMessageBtn);
        /*connectWebSocket*/

        try {
            user = new JSONObject(getActivity().getIntent().getStringExtra("user"));

            final String name = user.getString("name").toString();
            final String id = user.getString("userId").toString();
            final String studentNo = user.getString("studentNo").toString();
            final String stateName = studentNo + " " + name;

        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), "정보가 없습니다.", Toast.LENGTH_SHORT).show();
        }

        URI uri = null;
        try {
            uri = new URI("ws://175.207.52.44:8887");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {}
            @Override
            public void onMessage(String s) {
                final String message = s;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = (TextView)view.findViewById(R.id.chatContentView);
                        textView.setText(textView.getText() + "\n" + message);
                    }
                });
            }
            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }
            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }};
        try {
            mWebSocketClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(view);
            }
        });
        return view;
    }
    public void sendMessage(View view) {
        EditText editText = (EditText)view.findViewById(R.id.sendMessageEdit);
        try{
            mWebSocketClient.send(user.getString("studentNo").toString() +" "+user.getString("name").toString() +": "+ editText.getText().toString());
        } catch(Exception e){
            e.printStackTrace();
        }
        editText.setText("");
    }
}
