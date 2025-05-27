package com.eriksgda.hotel_management.model;

import com.eriksgda.hotel_management.enums.UserRole;

import java.util.UUID;

public record AuthResponseDTO(String token, UUID userId, UserRole role) {
}
