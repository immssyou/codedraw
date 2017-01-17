package com.example.cookandroid.lifepan_real;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cookandroid.lifepan_real.helper.ImageHelper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by immss_000 on 2015-11-29.
 */
public class KlifeBoardFragment extends Fragment{
    AsyncHttpClientLP client = new AsyncHttpClientLP();
    JSONObject user;
    ArrayList<BoardCustom> mBoard;
    BoardCustomAdapter mAdapter;
    ListView mListView;
    DisplayImageOptions options;


    public static KlifeBoardFragment newInstance() {
        KlifeBoardFragment fragment = new KlifeBoardFragment();
        return fragment;
    }

    public KlifeBoardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_main, container, false);
        View header = getActivity().getLayoutInflater().inflate(R.layout.listview_header, null, false);

        TextView editText = (TextView) header.findViewById(R.id.editText);
        LinearLayout writeText = (LinearLayout) header.findViewById(R.id.writeText);

        mListView = (ListView)view.findViewById(R.id.list);
        mListView.addHeaderView(header);
        options = ImageHelper.getInstance().getDisplayImageOptions(getActivity());
        mBoard = new ArrayList<BoardCustom>();
        mAdapter = new BoardCustomAdapter(getActivity().getApplicationContext(), R.layout.row, mBoard);
        mListView.setAdapter(mAdapter);
        mListView.requestFocusFromTouch();


        try {
            user = new JSONObject(getActivity().getIntent().getStringExtra("user"));

            final String name = user.getString("name").toString();
            final String id = user.getString("userId").toString();
            final String studentNo = user.getString("studentNo").toString();
            final String stateName = studentNo + " " + name;

        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), "정보가 없습니다.", Toast.LENGTH_SHORT).show();
        }

        RequestParams params = new RequestParams();
        client.post("getKlifeBoardList", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                mBoard.clear();
                try {
                    JSONArray kboardList = response.getJSONArray("board");
                    for (int i = 0; i < kboardList.length(); i++) {
                        JSONObject kboard = kboardList.getJSONObject(i);

                        String content = kboard.getString("content");
                        String registDate = kboard.getString("registDate");
                        String userName = kboard.getString("userName");
                        String imageURL = kboard.getString("imageURL");
                        BoardCustom board = new BoardCustom(R.drawable.default_profile, userName, registDate, content, imageURL);
                        mBoard.add(board);
                    }
                    mAdapter.notifyDataSetChanged();
                    mListView.setSelection(6);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(), "정보가 틀립니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

        writeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), KlifeBoardForm.class);
                intent.putExtra("user", user.toString());
                startActivity(intent);
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), KlifeBoardForm.class);
                intent.putExtra("user", user.toString());
                startActivity(intent);
            }
        });
        return view;
    }



    class BoardCustom {
        int imgUser;
        String textUserId, textDate, Text;
        String imageURL;
        BoardCustom(int imgUser, String textUserId, String textDate, String Text, String imageURL) {
            this.imgUser = imgUser;
            this.textUserId = textUserId;
            this.textDate = textDate;
            this.Text = Text;
            this.imageURL = imageURL;
        }
    }

    class BoardCustomAdapter extends BaseAdapter {
        Context context;
        LayoutInflater inflater;
        ArrayList<BoardCustom> boardCustoms;
        int layout;

        public BoardCustomAdapter(Context context, int layout, ArrayList<BoardCustom> boardCustoms) {
            this.context = context;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.boardCustoms = boardCustoms;
            this.layout = layout;
        }

        @Override
        public int getCount() {
            return boardCustoms.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(layout, parent, false);
            }
            ImageView userImg = (ImageView) convertView.findViewById(R.id.imgUser);
            userImg.setImageResource(boardCustoms.get(position).imgUser);

            TextView idText = (TextView) convertView.findViewById(R.id.textUserId);
            idText.setText(boardCustoms.get(position).textUserId);

            TextView dateText = (TextView) convertView.findViewById(R.id.textDate);
            dateText.setText(boardCustoms.get(position).textDate);

            TextView Text = (TextView) convertView.findViewById(R.id.Text);
            Text.setText(boardCustoms.get(position).Text);

            ImageView imageURL = (ImageView) convertView.findViewById(R.id.imageURL);
            ImageLoader.getInstance().displayImage(boardCustoms.get(position).imageURL, imageURL, options);

            final ImageView openMenu = (ImageView) convertView.findViewById(R.id.open_menu);
            openMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Toast.makeText(getActivity().getApplicationContext(), openMenu.getDrawable().toString(),Toast.LENGTH_LONG).show();
                }
            });
            return convertView;
        }
    }

}