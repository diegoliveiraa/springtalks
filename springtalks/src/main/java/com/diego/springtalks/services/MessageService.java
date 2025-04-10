package com.diego.springtalks.services;

import com.diego.springtalks.entities.ChatRoom;
import com.diego.springtalks.entities.Message;
import com.diego.springtalks.entities.User;
import com.diego.springtalks.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(String content, User sender, ChatRoom chatRoom) {

        Message newMessage = new Message();
        newMessage.setContent(content);
        newMessage.setTimestamp(LocalDateTime.now());
        newMessage.setSender(sender);
        newMessage.setChatRoom(chatRoom);

        this.save(newMessage);

        return newMessage;
    }

    public void save(Message newMessage) {
        this.messageRepository.save(newMessage);
    }


}
