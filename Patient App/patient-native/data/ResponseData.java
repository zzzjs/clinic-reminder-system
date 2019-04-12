package com.pitt.patient.data;

import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("resp_event")
    private int respEvent;

    @SerializedName("seq_id")
    private String seqId;

    @SerializedName("action")
    private String action;

    @SerializedName("respJson")
    private String respJson;

    public ResponseData() {

    }

    public ResponseData(int respEvent, String seqId, String action, String respJson) {
        this.respEvent = respEvent;
        this.seqId = seqId;
        this.action = action;
        this.respJson = respJson;
    }

    public int getRespEvent() {
        return respEvent;
    }

    public void setRespEvent(int respEvent) {
        this.respEvent = respEvent;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRespJson() {
        return respJson;
    }

    public void setRespJson(String respJson) {
        this.respJson = respJson;
    }
}
