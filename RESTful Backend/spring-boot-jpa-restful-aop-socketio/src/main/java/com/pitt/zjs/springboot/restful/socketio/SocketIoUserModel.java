package com.pitt.zjs.springboot.restful.socketio;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SocketIoUserModel {
	@ApiModelProperty(value="userID")
    private String userId;
    @ApiModelProperty(value="1.doctor、2.patient")
    private String userType;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
    
    
}
