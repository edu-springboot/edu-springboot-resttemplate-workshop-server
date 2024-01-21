package com.nhnacademy.springboot.accountserver.repository;

import com.nhnacademy.springboot.accountserver.domain.Account;
import com.nhnacademy.springboot.accountserver.domain.AccountIdConflictException;
import com.nhnacademy.springboot.accountserver.domain.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryAccountRepository implements AccountRepository {

    private final ConcurrentHashMap<Long, Account> data = new ConcurrentHashMap<>();
    @Override
    public Account findById(Long id) {
        return data.get(id);
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Account save(Account account) {
        return data.compute(account.getId(), (k, v) -> {
            if (v == null) {
                return account;
            }
            throw new AccountIdConflictException("Account already exist by Id : " + account.getId());
        });
    }

    @Override
    public Long delete(Long id) {
        return data.remove(id) != null? 1L:0L;
    }
}
