package com.pitt.zjs.springboot.restful.socketio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;

@Configuration
public class SocketIOConfig {
	/**
     * hostname
     */
    @Value("${socketio.hostname}")
    private String hostName;

    /**
     * port
     */
    @Value("${socketio.port}")
    private int port;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();

        String os = System.getProperty("os.name");
        //localhost
        if (os.toLowerCase().startsWith("mac")) {
            System.out.println("SocketIO HostName：localhost");
            config.setHostname("localhost");
        }
        //product ip
        else {
        	System.out.println("SocketIO HostName："+hostName);
            config.setHostname(hostName);
        }
        config.setPort(port);
        System.out.println("SocketIO Port："+port);

        final SocketIOServer server = new SocketIOServer(config);
        return server;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }
}
