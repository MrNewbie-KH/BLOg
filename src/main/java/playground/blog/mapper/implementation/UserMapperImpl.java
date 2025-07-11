package playground.blog.mapper.implementation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import playground.blog.dto.user.UserRequestDTO;
import playground.blog.dto.user.UserResponseDTO;
import playground.blog.entity.User;
import playground.blog.mapper.UserMapper;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(UserRequestDTO userRequestDTO) {
        return User.builder()
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword())
                .build();
    }

    @Override
    public UserResponseDTO toResponse(User user) {
       return UserResponseDTO.builder()
               .id(user.getId())
               .username(user.getUsername())
               .firstName(user.getFirstName())
               .lastName(user.getLastName())
               .isActive(user.isActive())
               .profilePicURL(user.getProfilePicURL())
               .bio(user.getBio())
               .build();
    }
}
