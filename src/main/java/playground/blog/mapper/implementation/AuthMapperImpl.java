package playground.blog.mapper.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import playground.blog.dto.auth.AuthenticateResponseDTO;
import playground.blog.dto.auth.RegisterRequestDTO;
import playground.blog.entity.Role;
import playground.blog.entity.User;
import playground.blog.mapper.AuthMapper;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class AuthMapperImpl implements AuthMapper {
    private final PasswordEncoder passwordEncoder;
    @Override
    public User toEntity(RegisterRequestDTO registerRequestDTO) {
        return User.builder()
                .firstName(registerRequestDTO.getFirstName())
                .lastName(registerRequestDTO.getLastName())
                .email(registerRequestDTO.getEmail())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .role(Role.USER)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    @Override
    public AuthenticateResponseDTO toAuthResponse(String token) {
        return AuthenticateResponseDTO.builder().token(token).build();
    }
}
