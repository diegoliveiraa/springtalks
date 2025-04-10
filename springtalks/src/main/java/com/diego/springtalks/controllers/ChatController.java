package com.diego.springtalks.controllers;

import com.diego.springtalks.dtos.requests.ChatMessageDTORequest;
import com.diego.springtalks.entities.ChatRoom;
import com.diego.springtalks.entities.User;
import com.diego.springtalks.services.ChatRoomService;
import com.diego.springtalks.services.MessageService;
import com.diego.springtalks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/chat.sendMessage")
    //@SendTo("/topic/chatrrom/{chatRoomId}")
    public void sendMessage(@Payload ChatMessageDTORequest chatMessageDTORequest) {

        System.out.println("Mensagem recebida via WebSocket: " + chatMessageDTORequest.content());


        User sender = userService.findById(chatMessageDTORequest.senderId())
                .orElseThrow(() -> new RuntimeException("User not found!"));
        ChatRoom chatRoom = chatRoomService.findWithParticipantsById(chatMessageDTORequest.chatRoomId())
                .orElseThrow(() -> new RuntimeException("ChatRoom not found!"));


        if (!chatRoom.getParticipants().contains(sender)) {
            throw new RuntimeException("User is not allowed in the room");
        }

        this.messageService.saveMessage(chatMessageDTORequest.content(), sender, chatRoom);

        ChatMessageDTORequest enrichedDTO = new ChatMessageDTORequest(
                chatMessageDTORequest.content(),
                sender.getId(),
                chatRoom.getId(),
                sender.getUsername()
        );

        String destination = "/topic/chatroom/" + chatRoom.getId();
        simpMessagingTemplate.convertAndSend(destination, enrichedDTO);
    }
}