package com.example.myapplication.http;

import com.example.myapplication.http.bean.request.LoginRequest;
import com.example.myapplication.http.bean.response.LoginResponse;
import com.example.myapplication.http.bean.response.LogoutResponse;
import com.example.myapplication.http.bean.response.SendCodeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Header;
import retrofit2.http.Body;
import retrofit2.http.Headers;
//接口定义

public interface ApiService {
    // 发送验证码
    @GET("/app/captcha/v1/login/emailCode")
    Call<SendCodeResponse> sendCode(
            @Query("email") String email,
            @Header("Tenant-Id") String tenantId,
            @Header("Client-Id") String clientId,
            @Header("App-Name") String appName,
            @Header("App-Ver") String appVer,
            @Header("Content-Languag") String contentLanguage
    );
    // 登录
    @POST("/auth/appLogin")
    @Headers("Content-Type: application/json")
    Call<LoginResponse> login(
            @Body LoginRequest request,
            @Header("Tenant-Id") String tenantId,
            @Header("Client-Id") String clientId,
            @Header("App-Name") String appName,
            @Header("App-Ver") String appVer,
            @Header("Content-Languag") String contentLanguage
    );

    @POST("/auth/logout")
    Call<LogoutResponse> logout(
            @Header("Authorization") String authorization,
            @Header("Tenant-Id") String tenantId,
            @Header("Client-Id") String clientId,
            @Header("App-Name") String appName,
            @Header("App-Ver") String appVer,
            @Header("Content-Languag") String contentLanguage
    );

}

