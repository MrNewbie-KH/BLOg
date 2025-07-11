package playground.blog.service;

import playground.blog.dto.user.UserRequestDTO;
import playground.blog.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(UserRequestDTO userRequestDTO);
    UserResponseDTO deleteUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUserById(UserRequestDTO userRequestDTO);
    List<UserResponseDTO> getAllUsers();

}
/*
create user // belong to auth but now for user
update user
delete user
get all users
get users by id
get user by criteria
 */
/*
authenticate
change password
reset password
 */
/*
verify email
resend verify email
activate/ deactivate user

 */