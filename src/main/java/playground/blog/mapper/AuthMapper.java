package playground.blog.mapper;

import playground.blog.dto.auth.AuthenticateResponseDTO;
import playground.blog.dto.auth.RegisterRequestDTO;
import playground.blog.entity.User;

public interface AuthMapper {
    public User toEntity(RegisterRequestDTO registerRequestDTO);
    public AuthenticateResponseDTO toAuthResponse(String token);
}
