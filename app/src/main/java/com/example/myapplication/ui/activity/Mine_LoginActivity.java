//package com.example.myapplication.ui.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.example.myapplication.R;
//import com.example.myapplication.http.RestClient;
//import com.example.myapplication.http.bean.response.LoginResponse;
//import com.example.myapplication.http.bean.response.LogoutResponse;
//
//import retrofit2.Call;
//import retrofit2.Response;
//
//public class Mine_LoginActivity extends AppCompatActivity {
//    private String token;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_mine_login);
//
//        token = getIntent().getStringExtra("token");
//
//
//        TextView textview_mine = findViewById(R.id.nav_mine);
//        textview_mine.setTextColor(0xFF0091EA);
//        textview_mine.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//
//
//        TextView textview_home = findViewById(R.id.nav_home);
//        textview_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(Mine_LoginActivity.this, HomeActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//        TextView quit = findViewById(R.id.quit);
//        quit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                quit();
//            }
//        });
//    }
//
//    private void quit(){
//        RestClient.getInstance().logout(token,new retrofit2.Callback<LogoutResponse>(){
//            @Override
//            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response){
//                token=null;
//                Intent intent = new Intent(Mine_LoginActivity.this, MineActivity.class);
//                startActivity(intent);
//                Toast.makeText(Mine_LoginActivity.this,"退出成功",Toast.LENGTH_SHORT).show();
//
//            }
//            @Override
//            public void onFailure(Call<LogoutResponse> call,Throwable t){
//                Toast.makeText(Mine_LoginActivity.this,"退出失败",Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
//}
//
