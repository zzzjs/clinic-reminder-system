package com.pitt.zjs.springboot.restful.socketio;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;

@Component
public class MessageEventHandler {
	public static SocketIOServer socketIoServer;
    static ArrayList<UUID> listClient = new ArrayList<>();

    @Autowired
    public MessageEventHandler(SocketIOServer server) {
        MessageEventHandler.socketIoServer = server;
    }

    @OnConnect
    public void onConnect(SocketIOClient client) {
        listClient.add(client.getSessionId());
        System.out.println("client:" + client.getSessionId() + "connected");
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        listClient.remove(client.getSessionId());
        System.out.println("client:" + client.getSessionId() + "disconnected");
    }

    @OnEvent(value = "client_message")
    public void onEvent(SocketIOClient client, AckRequest request, MessageInfo data) {
        System.out.println("client send msg：" + data.getMsgContent());
        socketIoServer.getClient(client.getSessionId()).sendEvent("messageevent", "back data");
    }

    @OnEvent(value = "user_connect")
    public void onEvent(SocketIOClient client, AckRequest request, SocketIoUserModel data) {
        System.out.println("client sent msg：" + data.getUserId());
        client.set("userId",data.getUserId());
        client.set("userType",data.getUserType());
        System.out.println("client sent msg：" + data.getUserType());
    }

    /**
     * push to front-end
     * @param userId   userID
     * @param userType 1.doctor、2.patient
     * @param josnStr  msg
     */
    public static void sendMessage(String userId, String userType, String josnStr) {
        System.out.println("push msg");

        for (UUID clientId : listClient) {
            SocketIOClient client = socketIoServer.getClient(clientId);
            if (client == null) continue;

            //front-end identify
            String siteCode = "goods_list_export_event";
            //userID
//            String _userId = client.getHandshakeData().getSingleUrlParam("userId"); //url
            String _userId = client.get("userId");
            if (StringUtils.isBlank(_userId) || !_userId.equals(userId)) {
                continue;
            }
            //userType
//            String _userType = client.getHandshakeData().getSingleUrlParam("userType");
            String _userType = client.get("userType");
            if (StringUtils.isBlank(_userType) || !_userType.equals(userType)) {
                continue;
            }
            System.out.println("push to front-end：" + userId + userType + josnStr);
            // push msg to front-end
            socketIoServer.getClient(clientId).sendEvent(siteCode, josnStr);
        }
    }
}
