package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    MessageService messageService;
    
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        if(createdAccount == null) {
            return ResponseEntity.status(409).body(createdAccount);
        }
        return ResponseEntity.status(200).body(createdAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> verifyAccount(@RequestBody Account account) {
        Account receivedAccount = accountService.verifyLogin(account);
        if(receivedAccount != null && account.getPassword().equals(receivedAccount.getPassword())) {
            return ResponseEntity.status(200).body(receivedAccount);
        }
        return ResponseEntity.status(401).body(receivedAccount);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        if(createdMessage != null) {
            return ResponseEntity.status(200).body(createdMessage);
        }
        return ResponseEntity.status(400).body(createdMessage);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> retrieveAllMessages() {
        List<Message> receivedMessages = messageService.getAllMessages();
        return ResponseEntity.status(200).body(receivedMessages);
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> retrieveMessageById(@PathVariable Integer message_id) {
        Message receivedMessage = messageService.getMessageById(message_id);
        if(receivedMessage != null) {
            return ResponseEntity.status(200).body(receivedMessage);
        }
        return ResponseEntity.status(200).body(null);
    }
}
