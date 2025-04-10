package com.diego.springtalks.dtos.responses;

import com.diego.springtalks.entities.Message;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageDTOResponse(
        UUID id,
        String content,
        LocalDateTime timestamp,
        String senderUsername
) {
    public static MessageDTOResponse fromEntity(Message message) {
        return new MessageDTOResponse(
                message.getId(),
                message.getContent(),
                message.getTimestamp(),
                message.getSender().getUsername()
        );
    }
}