package com.eriksgda.hotel_management.service.auth;

import com.eriksgda.hotel_management.entity.User;
import com.eriksgda.hotel_management.enums.UserRole;
import com.eriksgda.hotel_management.model.ResponseUserDTO;
import com.eriksgda.hotel_management.model.SignupRequestDTO;
import com.eriksgda.hotel_management.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        this.createAnAdminAccount();
    }

    private void createAnAdminAccount() {
        Optional<User> adminAccount = this.userRepository.findByUserRole(UserRole.ADMIN);

        if (adminAccount.isEmpty()) {
            User admin = User.builder()
                    .email("admin@test.com").name("Admin")
                    .userRole(UserRole.ADMIN)
                    .password(passwordEncoder.encode("admin")).build();

            this.userRepository.save(admin);

            System.out.println("Admin account created successfully.");
        } else {
            System.out.println("Admin account already exists.");
        }
    }

    @Override
    public ResponseUserDTO createUser(SignupRequestDTO dto) {
        if (this.userRepository.findFirstByEmail(dto.email()).isPresent()) {
            throw new EntityExistsException("User already exits with this email: " + dto.email());
        }
        User newUser = User.builder()
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .name(dto.name())
                .userRole(UserRole.CUSTOMER).build();

        this.userRepository.save(newUser);

        return ResponseUserDTO.fromEntity(newUser);
    }
}
