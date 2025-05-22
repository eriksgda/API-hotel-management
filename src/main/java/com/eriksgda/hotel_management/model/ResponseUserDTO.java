package com.eriksgda.hotel_management.model;

import com.eriksgda.hotel_management.entity.User;
import com.eriksgda.hotel_management.enums.UserRole;

import java.util.UUID;

public record ResponseUserDTO(UUID id, String email, String name, UserRole role) {
    public static ResponseUserDTO fromEntity(User user) {
        return new ResponseUserDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getUserRole()
        );
    }
}
