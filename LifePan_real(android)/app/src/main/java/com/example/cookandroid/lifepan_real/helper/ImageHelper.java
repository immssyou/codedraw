package com.example.cookandroid.lifepan_real.helper;

import android.content.Context;

import com.example.cookandroid.lifepan_real.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ImageHelper {
    //-----------싱글톤 객체 생성을 위한 준비 시작---------
    private static ImageHelper current;

    public static ImageHelper getInstance() {
        if (current == null) {
            current = new ImageHelper();
        }
        return current;
    }
    public static void freeInstance() {
        //객체 에 null을 대입하면 메모리에서 삭제된다.
        current = null;
    }

    //기본 생성자를 private 로 은닉하게 되면 new 를 통한 객체 생성이 금지된다.
    private ImageHelper() {
        super();
    }
    //-------------싱글톤 객체 생성을 위한 준비 끝------------

    public DisplayImageOptions getDisplayImageOptions(Context context) {
        //이미지 다운로더 초기화
        ImageLoader imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited()) {
            ImageLoaderConfiguration config
                    = ImageLoaderConfiguration.createDefault(context);
            imageLoader.init(config);
        }

        //이미지 표시 옵션 설정 (현재 프로젝트에 대한 R 클래스 import)
        //--> import study.android.imagelistex.R;
        DisplayImageOptions options = new DisplayImageOptions.Builder()
   /*             .showImageOnLoading(R.mipmap.ic_stub)
                .showImageForEmptyUri(R.mipmap.ic_empty)
                .showImageOnFail(R.mipmap.ic_error) */
                .cacheInMemory(true).cacheOnDisk(true)
                .considerExifParams(true)
                .build();

        return  options;
    }
}