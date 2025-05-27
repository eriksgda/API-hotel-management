package com.eriksgda.hotel_management.service.auth;

import com.eriksgda.hotel_management.entity.User;
import com.eriksgda.hotel_management.enums.UserRole;
import com.eriksgda.hotel_management.model.AuthRequestDTO;
import com.eriksgda.hotel_management.model.AuthResponseDTO;
import com.eriksgda.hotel_management.model.SignupResponseDTO;
import com.eriksgda.hotel_management.model.SignupRequestDTO;
import com.eriksgda.hotel_management.repository.UserRepository;
import com.eriksgda.hotel_management.service.UserService;
import com.eriksgda.hotel_management.util.JWTUtil;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthServiceImpl(
            UserRepository repository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JWTUtil jwtUtil,
            UserService userService) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
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
    public SignupResponseDTO createUser(SignupRequestDTO dto) {
        if (this.userRepository.findFirstByEmail(dto.email()).isPresent()) {
            throw new EntityExistsException("User already exits with this email: " + dto.email());
        }
        User newUser = User.builder()
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .name(dto.name())
                .userRole(UserRole.CUSTOMER).build();

        this.userRepository.save(newUser);

        return SignupResponseDTO.fromEntity(newUser);
    }

    @Override
    public AuthResponseDTO createAuthToken(AuthRequestDTO dto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
        } catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Incorrect email or password.");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(dto.email());

        User user = userRepository.findFirstByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("User authenticated but not found."));

        final String token = jwtUtil.generateToken(userDetails);

        return new AuthResponseDTO(token, user.getId(), user.getUserRole());
    }
}
