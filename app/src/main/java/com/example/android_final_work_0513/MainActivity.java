package com.example.android_final_work_0513;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.DnsResolver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MyAdapter.ListItemClickListener {

    private static final String TAG = "retrofit";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MyAdapter mAdapter;
    private List<ArticleResponse> mArticles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO 获取API中的数据

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mArticles = new ArrayList<>();
        mAdapter = new MyAdapter(MainActivity.this);

        recyclerView.setAdapter(mAdapter);


        getData();



        //TODO 底部的跳转按钮
        ImageButton uploadBT = (ImageButton) findViewById(R.id.bt_upload);
        uploadBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uploadI = new Intent(MainActivity.this, uploadFragmentActivity.class);
                startActivity(uploadI);
            }
        });

        ImageButton meBT = (ImageButton) findViewById(R.id.bt_me);
        meBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meIntent = new Intent(MainActivity.this, meFragmentActivity.class);

                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    Bundle bundle = extras.getBundle("user");
                    if (bundle != null) {

                        String nameMain = bundle.getString("userName");
                        String accountMain = bundle.getString("userAccount");

                        Bundle bundleNew = new Bundle();

                        bundleNew.putString("userName", nameMain);
                        bundleNew.putString("userAccount", accountMain);
                        meIntent.putExtra("user", bundle);

                    }
                }


                startActivity(meIntent);
            }
        });

    }


    private void getData() {
        //https://beiyou.bytedance.com/api/invoke/video/invoke/video
        //
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<ArticleResponse>> articles = apiService.getArticles();
        articles.enqueue(new Callback<List<ArticleResponse>>() {
            @Override
            public void onResponse(Call<List<ArticleResponse>> call, Response<List<ArticleResponse>> response) {
                //  Toast.makeText(MainActivity.this,response.body().toString(), Toast.LENGTH_SHORT).show();
                Log.d("tag", response.body().toString());
                if (response.body() != null) {
                    List<ArticleResponse> articles = response.body();
                    Log.d(TAG, articles.toString());
                    if (articles.size() != 0) {
                        mAdapter.setData(response.body());
                        mAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<ArticleResponse>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });




    }




    @Override
    public void onListItemClick(String titleindex, String videoUrl, String setName, String setLikeCount, String setDescription) {
        Log.d(TAG, "onListItemClick: " + titleindex);

        //携带数据，跳转到新的界面
        Intent intent = new Intent(this, VideoPlayerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", titleindex);
        bundle.putString("videoUrl", videoUrl);
        bundle.putString("setName", setName);
        bundle.putString("setLikeCount", setLikeCount);
        bundle.putString("setDescription", setDescription);

        intent.putExtra("user", bundle);
        startActivity(intent);


    }


}
