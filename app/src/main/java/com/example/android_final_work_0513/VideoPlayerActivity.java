package com.example.android_final_work_0513;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.video_player);

        TextView textNickName = findViewById(R.id.video_nickname);
        TextView textDescription = findViewById(R.id.video_description);
        TextView textLikeCount = findViewById(R.id.video_likecount);

        String videoUrl;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle bundle = extras.getBundle("user");
            if (bundle != null) {
                textNickName.setText(bundle.getString("setName"));
                textDescription.setText(bundle.getString("setDescription"));
                textLikeCount.setText(bundle.getString("setLikeCount"));

                videoUrl = bundle.getString("videoUrl");

                Uri playVideoUrl = Uri.parse(videoUrl);
                //TODO 播放视频
                videoView = findViewById(R.id.videoView);
                mediaController = new MediaController(this);
                videoView.setVideoURI(playVideoUrl);
                videoView.setMediaController(mediaController);
                videoView.start();

            }
        }


        ImageView heartImage = findViewById(R.id.video_heart);
        //TODO 更新likecount和like图标
        findViewById(R.id.video_heart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (heartImage.getDrawable().getCurrent().getConstantState()
                        .equals(getResources().getDrawable(R.drawable.heart_noclick).getConstantState())){

                    heartImage.setImageResource(R.drawable.heart_click);

                    int likeCountCurrent = Integer.parseInt(textLikeCount.getText().toString());
                    likeCountCurrent = likeCountCurrent + 1;
                    textLikeCount.setText(String.valueOf(likeCountCurrent));

                }
                else{
                    heartImage.setImageResource(R.drawable.heart_noclick);
                    int likeCountCurrent = Integer.parseInt(textLikeCount.getText().toString());
                    likeCountCurrent = likeCountCurrent - 1;
                    textLikeCount.setText(String.valueOf(likeCountCurrent));
                }
            }
        });







        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });

        videoView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (videoView.isPlaying()){
                    videoView.pause();
                }
                else{
                    videoView.start();
                }

                return false;
            }
        });

    }
}
