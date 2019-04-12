package com.pitt.zjs.springboot.restful.socketio;

import lombok.Data;

@Data
public class MessageInfo {
    private  String msgContent;

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
    
}	
