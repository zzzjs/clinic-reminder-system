package com.pitt.zjs.springboot.restful.net;

import com.google.gson.annotations.SerializedName;

public class PushResponse {
	
	@SerializedName("resp_event")
    private int respEvent;

    @SerializedName("seq_id")
    private String seqId;

    @SerializedName("action")
    private String action;

    @SerializedName("respJson")
    private String respJson;

	public PushResponse(int respEvent, String seqId, String action, String respJson) {
		super();
		this.respEvent = respEvent;
		this.seqId = seqId;
		this.action = action;
		this.respJson = respJson;
	}

    public PushResponse() {
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "PushResponse [respEvent=" + respEvent + ", seqId=" + seqId + ", action=" + action + ", respJson="
				+ respJson + "]";
	}    
    
}
