package com.example.websocketdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;


@RestController
@RequestMapping
public class HomeController {
    @Autowired
    private WebSocketConfig socketConfig;


    @GetMapping("/dispatcher")
    public int getDispatcher() {
        return  socketConfig.dispatcherSocketConnectionHandler.getSessionCount();
    }
//
    @GetMapping("/driver")
    public int getDriver() {
        return socketConfig.driverSocketConnectionHandler.getSessionCount();
    }
    @GetMapping("/sendMessage2drivers")
    public void sendMessage2drivers(String message) throws Exception {
        socketConfig.driverSocketConnectionHandler.broadcast(new TextMessage(message));
    }
    @GetMapping("/sendMessage2dispatcher")
    public void sendMessage2dispatcher(String message) throws Exception {
        socketConfig.dispatcherSocketConnectionHandler.broadcast(new TextMessage(message));
    }
}
