package com.nhnacademy.springboot.accountserver.domain;

import java.util.List;

public interface AccountService {

    List<Account> getAccounts();
    Account getAccount(Long id);
    void createAccount(Account account);
    void deleteAccount(Long id);

}