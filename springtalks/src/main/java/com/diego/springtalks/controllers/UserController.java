package com.diego.springtalks.controllers;

import com.diego.springtalks.dtos.requests.UserDTO;
import com.diego.springtalks.dtos.requests.UserDTORequest;
import com.diego.springtalks.dtos.responses.ChatRoomDTOResponse;
import com.diego.springtalks.dtos.responses.UserDTOResponseWithId;
import com.diego.springtalks.services.ChatRoomService;
import com.diego.springtalks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<UserDTORequest> register(@RequestBody UserDTO userDTO) {
        this.userService.register(userDTO);

        UserDTORequest newUserDTORequest = new UserDTORequest(
                userDTO.username(),
                userDTO.email()
        );
        return new ResponseEntity<>(newUserDTORequest, HttpStatus.CREATED);
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserDTOResponseWithId> getUserByEmail(@RequestParam String email) {
        return this.userService.findByEmail(email)
                .map(user -> ResponseEntity.ok(new UserDTOResponseWithId(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail())))
                .orElse(ResponseEntity.notFound().build()
                );

    }

    @GetMapping("/{id}/chatrooms")
    public ResponseEntity<List<ChatRoomDTOResponse>> getUserAndPublicChatRooms(@PathVariable UUID id) {
        return userService.findById(id)
                .map(user -> {
                    List<ChatRoomDTOResponse> privateRooms = user.getChatRooms().stream()
                            .map(chatRoom -> new ChatRoomDTOResponse(
                                    chatRoom.getId(),
                                    chatRoom.getName(),
                                    false // salas privadas
                            ))
                            .toList();

                    List<ChatRoomDTOResponse> publicRooms = chatRoomService.findPublicRooms().stream()
                            .map(chatRoom -> new ChatRoomDTOResponse(
                                    chatRoom.getId(),
                                    chatRoom.getName(),
                                    true // salas públicas
                            ))
                            .toList();

                    // Junta privadas + públicas (removendo duplicadas)
                    List<ChatRoomDTOResponse> allRooms = new ArrayList<>(privateRooms);
                    publicRooms.forEach(room -> {
                        if (!allRooms.contains(room)) {
                            allRooms.add(room);
                        }
                    });

                    return ResponseEntity.ok(allRooms);
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
