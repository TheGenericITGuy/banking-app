package net.javaguides.baking.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.baking.dto.AccountDto;
import net.javaguides.baking.entity.Account;
import net.javaguides.baking.mapper.AccountMapper;
import net.javaguides.baking.repository.AccountRepository;
import net.javaguides.baking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account = accountRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Account Not found from the id "+id));
        account.setBalance(account.getBalance()+amount);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account = accountRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Account Not found from the id "+id));
        double balance = account.getBalance();
        if(amount>balance){
            throw new RuntimeException("Insufficient balance");
        }else {
            account.setBalance(account.getBalance()-amount);
        }
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAccountById(Long id) {

        Account account = accountRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Account Not found from the id "+id));
        accountRepository.deleteById(id);
    }
}
