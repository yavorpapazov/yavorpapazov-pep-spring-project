package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;
    
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message) {
        Optional<Account> postedByAccount = accountRepository.findById(message.getPosted_by());
        if(postedByAccount.isPresent() && message.getMessage_text() != "") {
            return messageRepository.save(message);
        }
        return null;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer message_id) {
        Optional<Message> receivedMessage = messageRepository.findById(message_id);
        if(receivedMessage.isPresent()) {
            return receivedMessage.get();
        }
        return null;
    }

    public Integer deleteMessageById(Integer message_id) {
        boolean exists = messageRepository.existsById(message_id);
        if(exists) {
            messageRepository.deleteById(message_id);
            return 1;
        }
        return null;
    }

    public List<Message> getAllMessagesByUser(Integer posted_by) {
        List<Message> receivedMessages = messageRepository.getMessagesByPostedBy(posted_by);
        return receivedMessages;
    }
}
