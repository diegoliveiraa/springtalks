package com.diego.springtalks.dtos.requests;

import java.util.UUID;

public record ChatMessageDTORequest(String content, UUID senderId, UUID chatRoomId, String senderUsername) {
}
