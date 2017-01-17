package com.example.cookandroid.lifepan_real.helper;

/**
 * Created by immss_000 on 2015-11-22.
 */
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.DisplayMetrics;

public class PhotoHelper {
    //----------- 싱글톤 객체 생성 시작 ----------
    private static PhotoHelper current = null;

    public static PhotoHelper getInstance() {
        if (current == null) {
            current = new PhotoHelper();
        }
        return current;
    }

    public static void freeInstance() {
        current = null;
    }

    private PhotoHelper() {
        super();
    }
    //----------- 싱글톤 객체 생성 끝 ----------

    @SuppressLint("InlinedApi")
    public void callGallery(Activity activity, int requestCode) {
        Intent intent = null;

        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            //  intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        }

        activity.startActivityForResult(intent, requestCode);
    }

    public String getPhotoPath(Context context,
                               Intent intent) {
        return FileUtils.getPath(context, intent.getData());
    }

    public Bitmap getThumb(Activity activity, String path) {
        Bitmap bmp = null;

        /** 1) 화면 해상도 얻기 >> KitKat 호환 코드 */
        // 화면의 해상도 정보 관리 객체
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        // 화면의 가로,높이 크기 얻기
        int deviceWidth = dm.widthPixels;
        int deviceHeight = dm.heightPixels;

        // 긴 축을 골라낸다.
        int maxScale = deviceWidth;
        if (maxScale < deviceHeight) {
            maxScale = deviceHeight;
        }

        /** 2) 이미지 크기 얻기 */
        // 비트맵 이미지 로더의 옵션을 설정하기 위한 객체
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 비트맵을 바로 로드하지 말고 정보만 읽어오라고 설정
        options.inJustDecodeBounds = true;
        // 비트맵 파일 읽어오기 --> 옵션에 의해서 정보만 읽어온다.
        BitmapFactory.decodeFile(path, options);

        // 폭과 넓이 중에서 긴 축을 골라낸다.
        int fscale = options.outHeight;
        if (options.outWidth > fscale) {
            fscale = options.outWidth;
        }

        /** 3) 이미지 리사이징 */
        // 화면 크기보다 크다면?
        if (maxScale < fscale) {
            // 이미지의 사이즈를 maxScale로 나누어서 샘플링 사이즈 계산
            // ex) 이미지의 긴 축이 2400px이고 화면의 maxScale이 800이면,
            //     2400/800이 되어 3으로 지정된다.
            int sampleSize = fscale / maxScale;

            // 새로운 비트맵 옵션 생성
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            // 샘플 사이즈 설정 --> 3으로 설정하면 1/3 크기가 된다.
            options2.inSampleSize = sampleSize;
            // 실제로 비트맵을 읽어온다.
            bmp = BitmapFactory.decodeFile(path, options2);
        } else {
            // 사이즈가 적당하다면 그냥 읽는다.
            bmp = BitmapFactory.decodeFile(path);
        }

        return bmp;
    }
}