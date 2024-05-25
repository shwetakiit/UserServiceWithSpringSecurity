package kumari.shweta.userservice.userservice.controllers;

import kumari.shweta.userservice.userservice.dtos.LogOutRequestDTO;
import kumari.shweta.userservice.userservice.dtos.LoginRequestDTO;
import kumari.shweta.userservice.userservice.dtos.SignUpRequestDTO;
import kumari.shweta.userservice.userservice.dtos.UserDTO;
import kumari.shweta.userservice.userservice.models.Token;
import kumari.shweta.userservice.userservice.models.User;
import kumari.shweta.userservice.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UserController {

    private UserService userService;
    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody SignUpRequestDTO  requestDTO) {
        User user= userService.singUp(
                requestDTO.getEmail(),
                requestDTO.getPassword(),
                requestDTO.getName());

        return UserDTO.fromUser(user);
    }

    public Token loin(LoginRequestDTO requestDTO){
        return null;
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody  LogOutRequestDTO logOutRequestDTO){
        return null;
    }

    @PostMapping("/validate/{token}")
    public UserDTO    validateToken(@PathVariable String token){
return null;
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id){
       return null;
    }

}
