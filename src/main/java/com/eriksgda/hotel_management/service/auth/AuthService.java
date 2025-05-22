package com.eriksgda.hotel_management.service.auth;

import com.eriksgda.hotel_management.model.ResponseUserDTO;
import com.eriksgda.hotel_management.model.SignupRequestDTO;

public interface AuthService {
    public ResponseUserDTO createUser(SignupRequestDTO signupRequestDTO);
}
