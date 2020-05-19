package com.example.android_final_work_0513;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/*
杨睿
 create 2020.5.13


*/

public class meFragmentActivity extends AppCompatActivity {

    private ImageView meIconImage;
    private ImageView infoImage;

    private final static String meIconUrl = "https://pic3.zhimg.com/80/v2-b0ce6389fdd38161874af8ebb7d47de6_720w.jpg";
    private final static String infoUrl = "https://pic3.zhimg.com/80/v2-0c27c656b40d9e5ba6af79025872a067_720w.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        meIconImage = findViewById(R.id.userIcon);
        infoImage = findViewById(R.id.setPic);

        RequestOptions cropOptions = new RequestOptions();
        cropOptions = cropOptions.circleCrop();

        Glide.with(meFragmentActivity.this)
                .load(meIconUrl)
                .apply(cropOptions)
                .into(meIconImage);



        Glide.with(meFragmentActivity.this)
                .load(infoUrl)
                .into(infoImage);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle bundle = extras.getBundle("user");
            if (bundle != null) {

                TextView username = findViewById(R.id.setUserName);
                TextView useraccount = findViewById(R.id.setUserAccount);

                username.setText(bundle.getString("userName"));
                useraccount.setText(bundle.getString("userAccount"));



            }
        }



    }


}
