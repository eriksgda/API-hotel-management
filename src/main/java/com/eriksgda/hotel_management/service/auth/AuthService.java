package com.eriksgda.hotel_management.service.auth;

import com.eriksgda.hotel_management.model.SignupResponseDTO;
import com.eriksgda.hotel_management.model.SignupRequestDTO;

public interface AuthService {
    public SignupResponseDTO createUser(SignupRequestDTO signupRequestDTO);
}
