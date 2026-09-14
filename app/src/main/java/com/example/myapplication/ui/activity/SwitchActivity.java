package com.example.myapplication.ui.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.ui.fragment.CartFragment;
import com.example.myapplication.ui.fragment.HomeFragment;
import com.example.myapplication.ui.fragment.MessageFragment;
import com.example.myapplication.ui.fragment.MineFragment;

public class SwitchActivity extends AppCompatActivity {
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        radioGroup =findViewById(R.id.navigation);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,new HomeFragment())
                .commit();

        radioGroup.setOnCheckedChangeListener((group,checkedId) -> {
            if (checkedId == R.id.nav_home) {
                switchFragment(new HomeFragment());
            } else if (checkedId == R.id.nav_message) {
                switchFragment(new MessageFragment());
            } else if (checkedId == R.id.nav_cart){
                switchFragment(new CartFragment());
            } else if (checkedId == R.id.nav_mine){
                switchFragment(new MineFragment());
            }
        });
    }
    private void switchFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();
    }

}
