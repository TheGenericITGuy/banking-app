package net.javaguides.baking.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.baking.dto.AccountDto;
import net.javaguides.baking.entity.Account;
import net.javaguides.baking.mapper.AccountMapper;
import net.javaguides.baking.repository.AccountRepository;
import net.javaguides.baking.service.AccountService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = accountRepository.save(AccountMapper.mapToAccount(accountDto));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto getAccountById(Long id) {

        Account account = accountRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Account Not found from the id "+id));
        return AccountMapper.mapToAccountDto(account);
    }
}
