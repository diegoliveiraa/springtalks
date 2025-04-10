package com.diego.springtalks.dtos.responses;

import java.util.UUID;

public record UserDTOResponseWithId(UUID id, String username, String email) {
}
