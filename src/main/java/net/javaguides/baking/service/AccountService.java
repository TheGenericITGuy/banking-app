package net.javaguides.baking.service;

import net.javaguides.baking.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

}
