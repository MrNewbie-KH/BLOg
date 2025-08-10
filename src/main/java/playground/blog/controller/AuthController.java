package playground.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import playground.blog.dto.auth.AuthenticateResponseDTO;
import playground.blog.dto.auth.LoginRequestDTO;
import playground.blog.dto.auth.RegisterRequestDTO;
import playground.blog.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public AuthenticateResponseDTO register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return authService.register(registerRequestDTO);
    }
    @PostMapping("/authenticate")
    public AuthenticateResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return  null ;
    }
}
