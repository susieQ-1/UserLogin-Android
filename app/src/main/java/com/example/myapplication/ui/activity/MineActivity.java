//package com.example.myapplication.ui.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.myapplication.R;
//
//public class MineActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.fragment_mine);
//
//        TextView textview = findViewById(R.id.login);
//        textview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(MineActivity.this, MainActivity.class);
//                startActivity(intent);
//
//            }
//        });
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
//        TextView textview_home = findViewById(R.id.nav_home);
//        textview_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(MineActivity.this, HomeActivity.class);
//                startActivity(intent);
//
//            }
//        });
////
////        TextView textview_cart = findViewById(R.id.nav_cart);
////        textview_home.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent();
////                intent.setClass(MineActivity.this,CartActivity.class);
////                startActivity(intent);
////
////            }
////        });
////
////        TextView textview_message = findViewById(R.id.nav_message);
////        textview_home.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent();
////                intent.setClass(MineActivity.this,MessageActivity.class);
////                startActivity(intent);
////
////            }
////        });
//
//
//
//    }
//
//
//
//
//}
