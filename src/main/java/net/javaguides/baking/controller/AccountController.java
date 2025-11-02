package net.javaguides.baking.controller;

import lombok.AllArgsConstructor;
import net.javaguides.baking.dto.AccountDto;
import net.javaguides.baking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    //Add account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){

        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);

    }

    //Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

}
