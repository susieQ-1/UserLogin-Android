package com.example.myapplication.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.http.RestClient;
import com.example.myapplication.http.bean.response.SendCodeResponse;

import retrofit2.Call;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private EditText inputEditText;
    private Button sendButton;

    private String extractCode(String fullText) {
        int start = fullText.indexOf("（");
        int end = fullText.indexOf("）");
        return fullText.substring(start + 1, end);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinner = findViewById(R.id.codespinner);
        String[] countries = getResources().getStringArray(R.array.countrycode);

        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return countries.length;
            }

            @Override
            public Object getItem(int position) {
                return countries[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView;
                if (convertView == null) {
                    textView = new TextView(MainActivity.this);
                } else {
                    textView = (TextView) convertView;
                }
                textView.setText(extractCode(countries[position]));
                return textView;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView;
                if (convertView == null) {
                    textView = new TextView(MainActivity.this);
                } else {
                    textView = (TextView) convertView;
                }
                textView.setText(countries[position]);
                return textView;
            }
        };

        spinner.setAdapter(adapter);
        spinner.setSelection(3);

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
                Intent intent = new Intent(MainActivity.this, SwitchActivity.class);
                startActivity(intent);
            }
        });

        CheckBox checkBox = findViewById(R.id.choose);

        sendButton = findViewById(R.id.send);
        inputEditText = findViewById(R.id.input);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBox.isChecked()){
                    Toast.makeText(MainActivity.this,"请先同意用户协议",Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = inputEditText.getText().toString().trim();
                if (email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入邮箱", Toast.LENGTH_SHORT).show();
                    return;
                }

                sendVerificationCode(email);

            }
        });
    }


    private void sendVerificationCode(String email) {
        RestClient.getInstance().sendCode(email, new retrofit2.Callback<SendCodeResponse>(){
            @Override
            public void onResponse(Call<SendCodeResponse> call, Response<SendCodeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SendCodeResponse result = response.body();
                    if (result.code == 200) {
                        Toast.makeText(MainActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, VerifyActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "发送失败: " + result.msg, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "发送失败: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SendCodeResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "请求失败: " + t.getMessage(), Toast.LENGTH_LONG).show();
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
