package com.example.myapplication.ui.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.http.RestClient;
import com.example.myapplication.http.bean.response.LogoutResponse;
import com.example.myapplication.ui.activity.MainActivity;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class MineFragment extends Fragment {
    private View unLoginView;
    private View loginView;
    private TextView login;
    private TextView quit;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_mine,container,false);

        token = getToken();

        unLoginView = view.findViewById(R.id.Mine_unlogin);
        loginView = view.findViewById(R.id.Mine_login);
        login = view.findViewById(R.id.login);
        quit = view.findViewById(R.id.quit);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quit();
            }
        });

        if(token == null || token.isEmpty()){
            showUnLoginUI();
        } else {
            showLoginUI();
        }
        return view;
    }
    private void showLoginUI(){
        loginView.setVisibility(View.VISIBLE);
        unLoginView.setVisibility(View.GONE);
    }
    private void showUnLoginUI(){
        loginView.setVisibility(View.GONE);
        unLoginView.setVisibility(View.VISIBLE);
    }
    private void quit(){
        if(token == null ||token.isEmpty()){
            return;
        }
        RestClient.getInstance().logout(token,new retrofit2.Callback<LogoutResponse>(){
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response){
                clearToken();
                showUnLoginUI();
                Toast.makeText(getActivity(),"已退出",Toast.LENGTH_SHORT).show();
            }

            public void onFailure(Call<LogoutResponse> call, Throwable t){
                Toast.makeText(getActivity(),"退出失败",Toast.LENGTH_SHORT).show();
            }

        });
    }
    private String getToken(){
        SharedPreferences prefs = getActivity().getSharedPreferences("app",MODE_PRIVATE);
        return prefs.getString("token","");
    }
    private void clearToken(){
        SharedPreferences prefs = getActivity().getSharedPreferences("app",MODE_PRIVATE);
        prefs.edit().remove("token").apply();
        token = null;
    }
}
