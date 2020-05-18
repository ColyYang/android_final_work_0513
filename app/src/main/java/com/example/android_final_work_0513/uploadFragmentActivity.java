package com.example.android_final_work_0513;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/*
杨睿
 create 2020.5.13

 2020.5.14
    视频的拍摄和录制功能基本实现

*/

public class uploadFragmentActivity extends AppCompatActivity {


    private static String[] PERMISSION_STORAGE={
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };
    private static int REQUEST_PERSSION_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        findViewById(R.id.btn_picture).setOnClickListener(v -> {
            startActivity(new Intent(uploadFragmentActivity.this, uploadTakePictureActivity.class));
        });

        findViewById(R.id.btn_camera).setOnClickListener(v -> {
            startActivity(new Intent(uploadFragmentActivity.this, uploadTakeVideoActivity.class));
        });


    }


}
