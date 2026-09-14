package com.example.myapplication.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.ui.activity.MainActivity;


public class MessageFragment extends Fragment {
    private View loginView;
    private View unloginView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        loginView = view.findViewById(R.id.Mess_login);
        unloginView = view.findViewById(R.id.Mess_unlogin);

        String token = getToken();
        if(token == null || token.isEmpty()) {
            showUnLoginUI(view);
        } else {
            showLoginUI(view);
        }
        return view;
    }

    private void showUnLoginUI(View view) {
        unloginView.setVisibility(View.VISIBLE);
        loginView.setVisibility(View.GONE);

        Button mess_button = view.findViewById(R.id.mess_login);
        mess_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void showLoginUI(View view) {
        unloginView.setVisibility(View.GONE);
        loginView.setVisibility(View.VISIBLE);

    }

    private String getToken(){
        SharedPreferences prefs = getActivity().getSharedPreferences("app", Context.MODE_PRIVATE);
        return prefs.getString("token","");
    }
}
