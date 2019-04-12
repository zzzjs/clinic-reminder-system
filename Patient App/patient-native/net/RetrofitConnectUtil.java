package com.pitt.patient.net;

import android.util.Log;

import com.google.gson.Gson;
import com.pitt.patient.data.PatientData;
import com.pitt.patient.data.RequestData;
import com.pitt.patient.data.RetrofitRespData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnectUtil {

    // 使用Retrofit封装的方法
    private void request() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequestInterface request = retrofit.create(GetRequestInterface.class);
//        GetRequestInterface request2 = retrofit.create(GetRequestInterface.class);

        //对 发送请求 进行封装
        Call<RetrofitRespData> call = request.getDoctor();
//        Call<String> call = request2.getDoctor();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<RetrofitRespData>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<RetrofitRespData> call, Response<RetrofitRespData> response) {
                // 步骤7：处理返回的数据结果
                System.out.println("连接success");
                Log.e("success",response.body().toString());
                response.body().show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<RetrofitRespData> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });

    }

    // 使用Retrofit封装的方法
    private void put() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequestInterface request = retrofit.create(GetRequestInterface.class);
//        GetRequestInterface request2 = retrofit.create(GetRequestInterface.class);
        PatientData patientData = new PatientData(3, "Daisy", "W", "daisy@gmail.com", "12345", "Friday,Monday,Tuesday", "2");
        RequestData req = new RequestData("requestData",10,0,new Gson().toJson(patientData));
        //对 发送请求 进行封装
        Call<RetrofitRespData> call = request.upDatePatient(new Gson().toJson(req));
//        Call<String> call = request2.getDoctor();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<RetrofitRespData>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<RetrofitRespData> call, Response<RetrofitRespData> response) {
                // 步骤7：处理返回的数据结果
                System.out.println("连接success");
                Log.e("success",response.body().toString());
                response.body().show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<RetrofitRespData> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });

    }

    // 使用Retrofit封装的方法
    private void login() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequestInterface request = retrofit.create(GetRequestInterface.class);
        PatientData patientData = new PatientData(3, "Daisy", "W", "daisy@gmail.com", "12345", "Friday,Monday,Tuesday", "2");
//        RequestData req = new RequestData("requestData",10,0,new Gson().toJson(patientData));
        //对 发送请求 进行封装
        Call<RetrofitRespData> call = request.login("login",10,0,new Gson().toJson(patientData));

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<RetrofitRespData>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<RetrofitRespData> call, Response<RetrofitRespData> response) {
                // 步骤7：处理返回的数据结果
                System.out.println("连接success");
//                Log.e("success",response.body().toString());
//                response.body().show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<RetrofitRespData> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });

    }
}
