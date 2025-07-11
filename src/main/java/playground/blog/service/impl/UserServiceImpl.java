package playground.blog.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import playground.blog.dto.user.UserRequestDTO;
import playground.blog.dto.user.UserResponseDTO;
import playground.blog.entity.User;
import playground.blog.mapper.UserMapper;
import playground.blog.repository.UserRepository;
import playground.blog.service.UserService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        User user =  userRepository.save(userMapper.toEntity(userRequestDTO));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO deleteUser(UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO getUserById(UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
       return  userRepository.findAll().stream().map(user -> userMapper.toResponse(user)).collect(Collectors.toList());

    }
}
