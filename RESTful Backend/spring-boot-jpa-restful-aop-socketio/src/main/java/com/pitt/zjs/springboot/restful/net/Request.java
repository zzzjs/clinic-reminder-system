package com.pitt.zjs.springboot.restful.net;

import com.google.gson.annotations.SerializedName;

public class Request {
	
    @SerializedName("action")
    private String action;

	@SerializedName("reqEvent")
    private int reqEvent;

    @SerializedName("seqId")
    private String seqId;

    @SerializedName("reqJson")
    private String reqJson;
    
    public Request() {
		// TODO Auto-generated constructor stub
	}

	public Request(String action, int reqEvent, String seqId, String reqJson) {
		super();
		this.action = action;
		this.reqEvent = reqEvent;
		this.seqId = seqId;
		this.reqJson = reqJson;
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

	public String getSeqId() {
		return seqId;
	}

	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	public String getReqJson() {
		return reqJson;
	}

	public void setReqJson(String reqJson) {
		this.reqJson = reqJson;
	}
    
    
    
}
