package com.example.android_final_work_0513;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText eName = findViewById(R.id.username);
        EditText eAccount = findViewById(R.id.useraccount);
        EditText ePassword = findViewById(R.id.password);
        EditText eRepassword = findViewById(R.id.repassword);
        Button loginBT = findViewById(R.id.login);

        //TODO 用户点击登录按钮进行登录信息处理
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eName.getText().toString();
                String account = eAccount.getText().toString();
                String password = ePassword.getText().toString();
                String repassword = eRepassword.getText().toString();

                //用户信息非空
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(account) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
                    Toast.makeText(RegisterActivity.this, "参数不合法", Toast.LENGTH_SHORT).show();
                    return;
                }
                //密码不相等
                if (!TextUtils.equals(password, repassword)) {
                    Toast.makeText(RegisterActivity.this, "密码不相等", Toast.LENGTH_SHORT).show();
                    return;
                }

                //TODO 登录成功，通过intent传递本次用户登录的信息
                Button meBT = (Button) findViewById(R.id.login);
                meBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent meI = new Intent(RegisterActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();

                        bundle.putString("userName", name);
                        bundle.putString("userAccount", account);
                        meI.putExtra("user", bundle);
                        startActivity(meI);
                    }
                });

            }
        });
    }
}
