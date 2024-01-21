package com.nhnacademy.springboot.accountserver.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ConcurrentLinkedDeque<WebSocketSession> sessions;

    private final ObjectMapper objectMapper;

    public AccountServiceImpl(AccountRepository accountRepository, ConcurrentLinkedDeque<WebSocketSession> sessions, ObjectMapper objectMapper) {
        this.accountRepository = accountRepository;
        this.sessions = sessions;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void createAccount(Account account) {
        accountRepository.save(account);

        sessions.forEach(it -> {
            try {
                it.sendMessage(new TextMessage(objectMapper.writeValueAsString(account)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.delete(id);
    }
}
