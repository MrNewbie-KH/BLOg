package playground.blog.service;

import org.springframework.web.bind.annotation.RequestBody;
import playground.blog.dto.auth.AuthenticateResponseDTO;
import playground.blog.dto.auth.LoginRequestDTO;
import playground.blog.dto.auth.RegisterRequestDTO;

public interface AuthService {
    public AuthenticateResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) ;
    public AuthenticateResponseDTO register(@RequestBody RegisterRequestDTO registerRequestDTO) ;
}
