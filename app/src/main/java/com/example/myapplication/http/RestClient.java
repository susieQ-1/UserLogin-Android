package com.example.myapplication.http;

import com.example.myapplication.http.bean.response.LogoutResponse;
import com.example.myapplication.http.bean.response.SendCodeResponse;
import com.example.myapplication.http.bean.request.LoginRequest;
import com.example.myapplication.http.bean.response.LoginResponse;
//网络请求客户端
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RestClient {
    private static RestClient instance;
    private ApiService apiService;

    private RestClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 自定义 DNS
        Dns customDns = new Dns() {
            @Override
            public List<InetAddress> lookup(String hostname) throws UnknownHostException {
                if (hostname.equals("iot-api.xiaomoshou.com")) {
                    return Arrays.asList(InetAddress.getByName("120.55.166.115"));
                }
                return Dns.SYSTEM.lookup(hostname);
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .dns(customDns)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iot-api.xiaomoshou.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static synchronized RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }


    // ===================== 发送验证码 =====================
    public void sendCode(String email, Callback<SendCodeResponse> callback) {
        apiService.sendCode(
                email,
                "000000",
                "7d8f4a2b6c3e9f1a0d5e7b4c8a2f6d9e",
                "ir_toolkit",
                "1.0.0",
                "en_US"
        ).enqueue(callback);
    }

    // ===================== 登录 =====================
    public void login(String email, String emailCode, Callback<LoginResponse> callback) {
        LoginRequest request = new LoginRequest(email, emailCode);
        apiService.login(
                request,
                "000000",
                "7d8f4a2b6c3e9f1a0d5e7b4c8a2f6d9e",
                "ir_toolkit",
                "1.0.0",
                "en_US"
        ).enqueue(callback);
    }

    public void logout(String authorization, Callback<LogoutResponse> callback){
        apiService.logout(
                authorization,
                "000000",
                "7d8f4a2b6c3e9f1a0d5e7b4c8a2f6d9e",
                "ir_toolkit",
                "1.0.0",
                "en_US"
        ).enqueue(callback);
    }

}
