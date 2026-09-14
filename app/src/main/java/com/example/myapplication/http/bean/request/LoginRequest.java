package com.example.myapplication.http.bean.request;

public class LoginRequest {
    public String email;
    public String emailCode;
    public String grantType;

    public LoginRequest(String email, String emailCode) {
        this.email = email;
        this.emailCode = emailCode;
        this.grantType = "email";
    }
}