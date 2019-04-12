package com.pitt.patient.net;

import com.pitt.patient.data.RetrofitRespData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface GetRequestInterface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getCall();

    @GET("spring-crm-rest/api/doctors/3")
    Call<RetrofitRespData> getDoctor();

    @FormUrlEncoded
    @POST("spring-crm-rest/api/login")
    Call<RetrofitRespData> login(@Field("action")String action, @Field("reqEvent") int reqEvent,
    @Field("seqId")long seqId, @Field("reqJson") String reqJson);

    @PUT("spring-crm-rest/api/patients")
    Call<RetrofitRespData> upDatePatient(String json);
}
