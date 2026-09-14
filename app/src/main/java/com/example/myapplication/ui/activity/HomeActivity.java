//package com.example.myapplication.ui.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.myapplication.R;
//
//public class HomeActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.fragment_home);
//
//        TextView textview_home = findViewById(R.id.nav_home);
//        textview_home.setTextColor(0xFF0091EA);
//
//        EditText edittext = findViewById(R.id.search);
//        edittext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        TextView textview_mine = findViewById(R.id.nav_mine);
//        textview_mine.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(HomeActivity.this, MineActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//
//
//    }
//}
