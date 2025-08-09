package playground.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import playground.blog.dto.auth.AuthenticateResponseDTO;
import playground.blog.dto.auth.LoginRequestDTO;
import playground.blog.dto.auth.RegisterRequestDTO;
import playground.blog.entity.User;
import playground.blog.mapper.AuthMapper;
import playground.blog.repository.UserRepository;
import playground.blog.security.JWTService;
import playground.blog.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticateResponseDTO login(LoginRequestDTO loginRequestDTO) {
//        you gonna be provided by email and password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getEmail(),loginRequestDTO.getPassword()
                )
        );

        User user = userRepository
                        .findByEmail(loginRequestDTO.getEmail())
                                .orElseThrow();
        String token = jwtService.generateSimpleToken(user);

        System.out.println(token);
        return authMapper.toAuthResponse(token);
    }

    @Override
    public AuthenticateResponseDTO register(RegisterRequestDTO registerRequestDTO) {
//        create user and save it to database
        User user = authMapper.toEntity(registerRequestDTO);
        userRepository.save(user);
//        generate token to but saved   and sent to response
     return    authMapper.toAuthResponse(jwtService.generateSimpleToken(user));
    }
}
