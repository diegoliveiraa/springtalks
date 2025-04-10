package com.diego.springtalks.dtos.responses;

import java.util.UUID;

public record ChatRoomDTOResponse(UUID id, String name, boolean isPublic) {
}
