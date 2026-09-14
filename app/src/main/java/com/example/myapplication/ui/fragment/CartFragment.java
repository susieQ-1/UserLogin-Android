package com.example.myapplication.ui.fragment;

import static android.content.Context.MODE_PRIVATE;

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

public class CartFragment extends Fragment {
    private View unLoginView;
    private View loginView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        unLoginView = view.findViewById(R.id.Cart_unlogin);
        loginView = view.findViewById(R.id.Cart_login);

        String token = getToken();
        if (token == null || token.isEmpty()) {
            showUnLoginUI(view);
        } else {
            showLoginUI(view);
        }
        return view;
    }

    private void showLoginUI(View view){
        loginView.setVisibility(View.VISIBLE);
        unLoginView.setVisibility(View.GONE);

    }

    private void showUnLoginUI(View view){
        loginView.setVisibility(View.GONE);
        unLoginView.setVisibility(View.VISIBLE);

        Button cart_button = view.findViewById(R.id.cart_login);
        cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private String getToken(){
        SharedPreferences prefs = getActivity().getSharedPreferences("app",MODE_PRIVATE);
        return prefs.getString("token","");
    }
}
