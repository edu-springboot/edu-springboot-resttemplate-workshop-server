package com.nhnacademy.springboot.accountserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentLinkedDeque;

@Configuration
public class WebSocketSessionConfiguration {

    @Bean
    ConcurrentLinkedDeque<WebSocketSession> webSocketSessions() {
        return new ConcurrentLinkedDeque<>();
    }
}
