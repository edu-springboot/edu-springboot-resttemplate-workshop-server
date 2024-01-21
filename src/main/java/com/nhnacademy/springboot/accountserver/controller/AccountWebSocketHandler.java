package com.nhnacademy.springboot.accountserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springboot.accountserver.domain.AccountService;
import com.nhnacademy.springboot.accountserver.domain.AccountServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedDeque;

@Component
public class AccountWebSocketHandler extends TextWebSocketHandler {

    private final ConcurrentLinkedDeque<WebSocketSession> webSocketSessions;
    private final ObjectMapper objectMapper;
    private final AccountService accountService;

    public AccountWebSocketHandler(ConcurrentLinkedDeque<WebSocketSession> webSocketSessions, ObjectMapper objectMapper, AccountService accountService) {
        this.webSocketSessions = webSocketSessions;
        this.objectMapper = objectMapper;
        this.accountService = accountService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.add(session);
        accountService.getAccounts()
                        .forEach(it -> {
                            try {
                                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(it)));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session);
    }
}
