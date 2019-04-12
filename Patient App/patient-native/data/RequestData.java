package com.pitt.patient.data;

import com.google.gson.annotations.SerializedName;

public class RequestData {

    @SerializedName("action")
    private String action;

    @SerializedName("reqEvent")
    private int reqEvent;

    @SerializedName("seqId")
    private long seqId;

    @SerializedName("reqJson")
    private String reqJson;

//    private transient int reqCount;

    public RequestData() {
    }

    public RequestData(String action, int reqEvent, long seqId, String req) {
        this.action = action;
        this.reqEvent = reqEvent;
        this.seqId = seqId;
        this.reqJson = req;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getReqEvent() {
        return reqEvent;
    }

    public void setReqEvent(int reqEvent) {
        this.reqEvent = reqEvent;
    }

    public long getSeqId() {
        return seqId;
    }

    public void setSeqId(long seqId) {
        this.seqId = seqId;
    }

    public String getReq() {
        return reqJson;
    }

    public void setReq(String reqJson) {
        this.reqJson = reqJson;
    }

}
