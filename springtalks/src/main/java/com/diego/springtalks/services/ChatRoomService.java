package com.diego.springtalks.services;

import com.diego.springtalks.dtos.requests.ChatRoomDTORequest;
import com.diego.springtalks.entities.ChatRoom;
import com.diego.springtalks.entities.User;
import com.diego.springtalks.repositories.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private UserService userService;

    public ChatRoom createRoom(ChatRoomDTORequest chatRoomDTORequest) {

        ChatRoom newChatRoom = new ChatRoom();

        newChatRoom.setName(chatRoomDTORequest.name());
        List<User> users = this.userService.findAllById(chatRoomDTORequest.participantsIds());
        newChatRoom.setParticipants(users);

        this.saveChatRoom(newChatRoom);

        return newChatRoom;
    }

    public void saveChatRoom(ChatRoom chatRoom) {
        this.chatRoomRepository.save(chatRoom);
    }

    public Optional<ChatRoom> findById(UUID uuid) {
        return this.chatRoomRepository.findById(uuid);
    }

    public Optional<ChatRoom> findWithParticipantsById(UUID id) {
        return chatRoomRepository.findWithParticipantsById(id);
    }

    public List<ChatRoom> findPublicRooms() {
        return chatRoomRepository.findByParticipantsIsEmpty();
    }
}
