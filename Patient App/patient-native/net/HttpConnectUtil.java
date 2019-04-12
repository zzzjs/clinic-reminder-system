package com.pitt.patient.net;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.pitt.patient.data.PatientData;
import com.pitt.patient.data.RequestData;
import com.pitt.patient.define;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnectUtil extends Thread{

    private Handler handler = null;
    private RequestData requestData;

    @Override
    public void run() {
        String baseURL = new define().url+":8080/spring-crm-rest/api/login";
        HttpURLConnection conn = null;
        try {
            URL target = new URL(baseURL);
            conn = (HttpURLConnection) target.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            Gson gson = new Gson();
            String jsonString = gson.toJson(requestData);
            wr.writeBytes(jsonString);
            wr.flush();
            wr.close();
            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String resp = br.readLine();
                Bundle b = new Bundle();
                b.putString("msg", resp);
                Message m = new Message();
                m.what=0;
                m.setData(b);
                handler.sendMessage(m);
            } else {
                Log.e("FAILED", " - POST");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public RequestData getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestData requestData) {
        this.requestData = requestData;
    }
}
