package playground.blog.mapper;


import playground.blog.dto.user.UserRequestDTO;
import playground.blog.dto.user.UserResponseDTO;
import playground.blog.entity.User;

public interface UserMapper {
    User toEntity (UserRequestDTO userRequestDTO);
    UserResponseDTO toResponse(User user);
}
