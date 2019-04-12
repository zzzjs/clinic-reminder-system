package com.pitt.patient.net;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.orhanobut.logger.Logger;
import com.pitt.patient.data.PatientData;
import com.pitt.patient.data.ReminderData;
import com.pitt.patient.data.RequestData;
import com.pitt.patient.data.ResponseData;
import com.pitt.patient.define;
import com.pitt.patient.util.Codec;
import com.pitt.patientapp.BuildConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class WsManager {
    private static final WsManager ourInstance = new WsManager();
    private final String TAG = this.getClass().getSimpleName();
    private Handler mainHanler;

    public static WsManager getInstance() {
        return ourInstance;
    }

    private WsManager() {
    }

    /**
     * WebSocket config
     */
    private static final int FRAME_QUEUE_SIZE = 5;
    private static final int CONNECT_TIMEOUT = 5000;
    private String url;
    private static final int REQUEST_TIMEOUT = 10000;//请求超时时间
    private AtomicLong seqId = new AtomicLong(SystemClock.uptimeMillis());//每个请求的唯一标识


    private WsStatus mStatus;
    private WebSocket ws;
    private WsListener mListener;


    public void init(Handler mainHanler){
        this.mainHanler = mainHanler;
        try {
            url = new define().wsurl+":8080/spring-crm-rest/websocket";
            ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT)
                    .setFrameQueueSize(FRAME_QUEUE_SIZE)
                    .setMissingCloseFrameAllowed(false)
                    .addListener(mListener = new WsListener())
                    .connectAsynchronously().sendText("test").sendPing("test");
            setStatus(WsStatus.CONNECTING);
            Logger.t(TAG).d("第一次连接");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 继承默认的监听空实现WebSocketAdapter,重写我们需要的方法
     * onTextMessage 收到文字信息
     * onConnected 连接成功
     * onConnectError 连接失败
     * onDisconnected 连接关闭
     */
    class WsListener extends WebSocketAdapter {

        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            Logger.t(TAG).d(text);
            ResponseData responseData = Codec.decoder(text);
            if (responseData.getRespEvent() == 10) {
                PatientData patientData = Codec.decoderChildResp(responseData.getRespJson());
                Logger.t(patientData.toString());
                Message m = new Message();
                m.what=20;
                Bundle b = new Bundle();
                b.putString("msg",new Gson().toJson(patientData));
                m.setData(b);
                mainHanler.sendMessage(m);
            } else if (responseData.getRespEvent() == 20) {
                PatientData patientData = Codec.decoderChildResp(responseData.getRespJson());
                Logger.t(TAG).d(patientData.toString());
            } else if (responseData.getRespEvent() == 100) {
                ArrayList<ReminderData> reminderDataList = new ArrayList<>();
                reminderDataList = Codec.decoderChildRemindersResp(responseData.getRespJson());
                for (ReminderData reminderData: reminderDataList) {
                    Logger.t(TAG).d(reminderData.toString());
                }
                Message m = new Message();
                m.what=20;
                Bundle b = new Bundle();
                b.putString("msg",responseData.getRespJson());
                m.setData(b);
                mainHanler.sendMessage(m);
            }
        }


        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers)
                throws Exception {
            super.onConnected(websocket, headers);
            Logger.t(TAG).d("连接成功");
            setStatus(WsStatus.CONNECT_SUCCESS);
            Message m = new Message();
            m.what=100;
            mainHanler.sendMessage(m);
        }


        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception)
                throws Exception {
            super.onConnectError(websocket, exception);
            Logger.t(TAG).d("连接错误");
            setStatus(WsStatus.CONNECT_FAIL);
        }


        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer)
                throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            Logger.t(TAG).d("断开连接");
            setStatus(WsStatus.CONNECT_FAIL);
        }
    }

    private void setStatus(WsStatus status){
        this.mStatus = status;
    }

    private WsStatus getStatus(){
        return mStatus;
    }

    public void disconnect(){
        if(ws != null)
            ws.disconnect();
    }

    public enum WsStatus {
        CONNECT_SUCCESS,//连接成功
        CONNECT_FAIL,//连接失败
        CONNECTING;//正在连接
    }

    public void sendReq(String action, int reqEvent, long seqId, PatientData patientData){
        Gson gson = new Gson();
        RequestData requestData = new RequestData(action,reqEvent,seqId,gson.toJson(patientData));
        ws.sendText(gson.toJson(requestData));
    }


    public void sendReq(ReminderData reminderData) {

        Gson gson = new Gson();
        RequestData requestData = new RequestData("removeReminder",10,0,gson.toJson(reminderData));

        ws.sendText(gson.toJson(requestData));
    }


    /**
     * @param action Action
     * @param req 请求参数
     * @param callback 回调
     * @param timeout 超时时间
     * @param reqCount 请求次数
     */
    @SuppressWarnings("unchecked")
    private <T> void sendReq(Action action, T req, ICallback callback, long timeout, int reqCount) {
//        RequestData request = new RequestData.Builder<T>()
//                .action(action.getAction())
//                .reqEvent(action.getReqEvent())
//                .seqId(seqId.getAndIncrement())
//                .reqCount(reqCount)
//                .req(req)
//                .build();
//
//        Logger.t(TAG).d("send text : %s", new Gson().toJson(request));
//        ws.sendText(new Gson().toJson(request));
        ws.sendText("Hello world!");
    }
    public enum Action {
        LOGIN("login", 1, null);

        private String action;
        private int reqEvent;
        private Class respClazz;


        Action(String action, int reqEvent, Class respClazz) {
            this.action = action;
            this.reqEvent = reqEvent;
            this.respClazz = respClazz;
        }


        public String getAction() {
            return action;
        }


        public int getReqEvent() {
            return reqEvent;
        }


        public Class getRespClazz() {
            return respClazz;
        }

    }
    public interface ICallback<T> {

        void onSuccess(T t);

        void onFail(String msg);

    }


}
