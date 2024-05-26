package kumari.shweta.userservice.userservice.services;

import kumari.shweta.userservice.userservice.models.Token;
import kumari.shweta.userservice.userservice.models.User;
import kumari.shweta.userservice.userservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    UserService(BCryptPasswordEncoder bCryptPasswordEncoder,UserRepository userRepository){
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.userRepository=userRepository;
    }
    public User singUp(String email, String password,String name) {
      User user = new User();
      user.setEmail(email);
      user.setName(name);
      user.setHashedpassword(bCryptPasswordEncoder.encode(password));
      user.setEmailVerified(true);
      //Save the user object to the data base
        return userRepository.save(user);

    }
    public Token login(String email, String password){
        return null;
    }

    public void logOut(String token){
        return;

    }

    public User validateToken(String token){
        return null;
    }
}
