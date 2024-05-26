package kumari.shweta.userservice.userservice.controllers;

import kumari.shweta.userservice.userservice.dtos.LogOutRequestDTO;
import kumari.shweta.userservice.userservice.dtos.LoginRequestDTO;
import kumari.shweta.userservice.userservice.dtos.SignUpRequestDTO;
import kumari.shweta.userservice.userservice.dtos.UserDTO;
import kumari.shweta.userservice.userservice.models.Token;
import kumari.shweta.userservice.userservice.models.User;
import kumari.shweta.userservice.userservice.repositories.TokenRepository;
import kumari.shweta.userservice.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UserController {

    private  TokenRepository tokenRepository;
    private UserService userService;
    UserController(UserService userService, TokenRepository tokenRepository){
        this.userService = userService;
        this.tokenRepository = tokenRepository;
    }

    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody SignUpRequestDTO  requestDTO) {
        User user= userService.singUp(
                requestDTO.getEmail(),
                requestDTO.getPassword(),
                requestDTO.getName());

        return UserDTO.fromUser(user);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDTO requestDTO){
      Token token=  userService.login(requestDTO.getEmail(),requestDTO.getPassword());
        return token;
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody  LogOutRequestDTO logOutRequestDTO){
       userService.logOut(logOutRequestDTO.getToken());
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/validate/{token}")
    public UserDTO    validateToken(@PathVariable String token){
        User user = userService.validateToken(token);
        return UserDTO.fromUser(user);
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id){
       return null;
    }

}
