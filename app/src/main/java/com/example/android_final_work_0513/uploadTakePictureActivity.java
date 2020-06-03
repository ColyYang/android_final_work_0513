package com.example.android_final_work_0513;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class uploadTakePictureActivity extends AppCompatActivity {

    private File imgFile;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static int REQUEST_PERSSION_CODE=1;
    private static String[] PERMISSION_STORAGE={
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };
    private static final int REQUEST_EXTERNAL_STORAGE = 101;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        findViewById(R.id.btn_picture).setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(uploadTakePictureActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(uploadTakePictureActivity.this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //TODO 申请相机、存储的权限
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,PERMISSION_STORAGE,REQUEST_PERSSION_CODE);
                }
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,PERMISSION_STORAGE,REQUEST_PERSSION_CODE);
                }
            } else {
                takePicture();
            }
        });

    }

    private void takePicture() {
        //TODO 打开相机
        Intent takePictureIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imgFile = Utils.getOutputMediaFile(Utils.MEDIA_TYPE_IMAGE);
        //sdk版本
        if(imgFile!=null){
            Uri fileUri;
            if(Build.VERSION.SDK_INT>=24) {
                fileUri = FileProvider.getUriForFile(this, "com.example.android_final_work_0513", imgFile);
            }
            else {
                fileUri= Uri.fromFile(imgFile);
            }
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                //TODO 判断权限是否已经授予
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&grantResults[1]== PackageManager.PERMISSION_GRANTED){
                    takePicture();
                }
                break;
            }
        }
    }
}
