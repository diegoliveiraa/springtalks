package com.diego.springtalks.controllers;

import com.diego.springtalks.dtos.requests.ChatRoomDTORequest;
import com.diego.springtalks.dtos.responses.MessageDTOResponse;
import com.diego.springtalks.entities.ChatRoom;
import com.diego.springtalks.services.ChatRoomService;
import com.diego.springtalks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ChatRoomDTORequest> createRoom(@RequestBody ChatRoomDTORequest chatRoomDTORequest) {
        // System.out.println("Nome: " + chatRoomDTO.name() + "Lista de Participantes: " + chatRoomDTO.participantsIds());
        ChatRoom chatRoom = this.chatRoomService.createRoom(chatRoomDTORequest);

        ChatRoomDTORequest newChatRoomDTORequest = new ChatRoomDTORequest(
                chatRoom.getId(),
                chatRoomDTORequest.name(),
                chatRoomDTORequest.participantsIds()
        );


        return new ResponseEntity<>(newChatRoomDTORequest, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/messages")
    public ResponseEntity<List<MessageDTOResponse>> getMessages(@PathVariable UUID id) {
        return ResponseEntity.ok(
                chatRoomService.findWithParticipantsById(id)
                        .map(ChatRoom::getMessages)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(MessageDTOResponse::fromEntity)
                        .toList()
        );
    }
}
