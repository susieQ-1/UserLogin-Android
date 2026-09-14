package com.example.myapplication.http.bean.response;

public class LoginResponse {
    public int code;
    public String msg;
    public LoginData data;

    public static class LoginData {
        public String access_token;
        public String refresh_token;
        public long expire_in;
        public long refresh_expire_in;
        public String client_id;
        public String scope;
        public String openid;
    }
}