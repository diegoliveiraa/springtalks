package com.diego.springtalks.dtos.requests;

import java.util.List;
import java.util.UUID;

public record ChatRoomDTORequest(UUID id, String name, List<UUID> participantsIds) {
}
