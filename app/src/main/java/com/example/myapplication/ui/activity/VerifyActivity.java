package com.example.myapplication.ui.activity;

import android.app.AlertDialog;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.http.RestClient;
import com.example.myapplication.http.bean.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Response;

public class VerifyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_verify);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button helpButton = findViewById(R.id.help);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelpDialog();
            }
        });

        ImageButton imageButton = findViewById(R.id.gd);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoreDialog();
            }
        });

        Button buttonback = findViewById(R.id.back);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerifyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        EditText codeInput = findViewById(R.id.code_input);
        String email = getIntent().getStringExtra("email");
        codeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 4){
                    String code = s.toString();
                    doLogin(email,code);
                }

            }
        });
    }

    private void doLogin(String email, String code) {
        RestClient.getInstance().login(email, code, new retrofit2.Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse result = response.body();
                    if (result.code == 200) {
                        String token = result.data.access_token;
                        Toast.makeText(VerifyActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                        SharedPreferences prefs = getSharedPreferences("app",MODE_PRIVATE);
                        prefs.edit().putString("token",token).apply();

                        Intent intent = new Intent(VerifyActivity.this, SwitchActivity.class);
                        intent.putExtra("token", token);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(VerifyActivity.this, "验证码错误: " + result.msg, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(VerifyActivity.this, "验证失败: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(VerifyActivity.this, "请求失败: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }





    private void showHelpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_help, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(android.view.Gravity.BOTTOM);

        TextView helpItem1 = dialogView.findViewById(R.id.help_item1);
        TextView helpItem2 = dialogView.findViewById(R.id.help_item2);
        TextView helpItem3 = dialogView.findViewById(R.id.help_cancel);
        TextView helpItem4 = dialogView.findViewById(R.id.help_close);

        helpItem1.setOnClickListener(v -> {});
        helpItem2.setOnClickListener(v -> {});
        helpItem3.setOnClickListener(v -> dialog.dismiss());
        helpItem4.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    private void showMoreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_more, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(android.view.Gravity.BOTTOM);

        TextView textview1 = dialogView.findViewById(R.id.more_close);
        textview1.setOnClickListener(v -> dialog.dismiss());

        ImageButton button1 = dialogView.findViewById(R.id.zfb);
        ImageButton button2 = dialogView.findViewById(R.id.tb);
        ImageButton button3 = dialogView.findViewById(R.id.wx);
        ImageButton button4 = dialogView.findViewById(R.id.pg);
        ImageButton button5 = dialogView.findViewById(R.id.mm);

        button1.setOnClickListener(v -> {});
        button2.setOnClickListener(v -> {});
        button3.setOnClickListener(v -> {});
        button4.setOnClickListener(v -> {});
        button5.setOnClickListener(v -> {});

        dialog.show();
    }


}
