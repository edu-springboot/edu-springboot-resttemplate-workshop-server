package com.nhnacademy.springboot.accountserver.domain;

import java.util.List;

public interface AccountRepository {
    Account findById(Long id);
    List<Account> findAll();
    Account save(Account account);
    Long delete(Long id);

}
