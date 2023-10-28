package com.example.controller;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
	AccountService accountService;
    
    @Autowired
    public SocialMediaController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/register")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        if(createdAccount == null) {
            return ResponseEntity.status(409).body(createdAccount);
        }
        return ResponseEntity.status(200).body(createdAccount);
    }
}
