package com.eriksgda.hotel_management.controller.auth;

import com.eriksgda.hotel_management.model.ResponseUserDTO;
import com.eriksgda.hotel_management.model.SignupRequestDTO;
import com.eriksgda.hotel_management.service.auth.AuthService;
import com.eriksgda.hotel_management.service.auth.AuthServiceImpl;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequestDTO data) {
        try {
            ResponseUserDTO response = this.authService.createUser(data);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (EntityExistsException exception) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
