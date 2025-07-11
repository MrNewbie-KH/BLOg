package playground.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import playground.blog.dto.user.UserRequestDTO;
import playground.blog.dto.user.UserResponseDTO;
import playground.blog.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping( "/api/users")
public class UserController {
    private final UserService userService;
    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {

        List<UserResponseDTO> ll =   userService.getAllUsers();
        System.out.println(ll.toString());
        return ll;
    }
}
