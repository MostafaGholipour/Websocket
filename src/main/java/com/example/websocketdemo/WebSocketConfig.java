package com.example.websocketdemo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// web socket connections is handled
// by this class
@Configuration
@EnableWebSocket
public class WebSocketConfig
        implements WebSocketConfigurer {

    SocketConnectionHandler driverSocketConnectionHandler;
    SocketConnectionHandler dispatcherSocketConnectionHandler;

    public SocketConnectionHandler getDriverSocketConnectionHandler() {
        return driverSocketConnectionHandler;
    }

    public SocketConnectionHandler getDispatcherSocketConnectionHandler() {
        return dispatcherSocketConnectionHandler;
    }


    // Overriding a method which register the socket
    // handlers into a Registry
    @Override
    public void registerWebSocketHandlers(
            WebSocketHandlerRegistry webSocketHandlerRegistry)
    {
        // For adding a Handler we give the Handler class we
        // created before with End point Also we are managing
        // the CORS policy for the handlers so that other
        // domains can also access the socket
        driverSocketConnectionHandler=new SocketConnectionHandler();
        dispatcherSocketConnectionHandler=new SocketConnectionHandler();

        webSocketHandlerRegistry
                .addHandler(driverSocketConnectionHandler,"/driver-socket")
                .setAllowedOrigins("*");
        webSocketHandlerRegistry
                .addHandler(dispatcherSocketConnectionHandler,"/dispatcher-socket")
                .setAllowedOrigins("*");
    }
}