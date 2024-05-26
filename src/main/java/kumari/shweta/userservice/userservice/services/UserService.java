package kumari.shweta.userservice.userservice.services;

import kumari.shweta.userservice.userservice.exceptions.UserNotFoundException;
import kumari.shweta.userservice.userservice.models.Token;
import kumari.shweta.userservice.userservice.models.User;
import kumari.shweta.userservice.userservice.repositories.TokenRepository;
import kumari.shweta.userservice.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private  TokenRepository tokenRepository;

    UserService(BCryptPasswordEncoder bCryptPasswordEncoder,
                UserRepository userRepository,
                TokenRepository tokenRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
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

    /**
     * Login with email and password
     * @param email
     * @param password
     * @return
     */

    public Token login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
        User user = optionalUser.get();

        //Validate password
        if (!bCryptPasswordEncoder.matches(password, user.getHashedpassword())) {
            //Throw some exception

            return null;
        }
        //Login successfully
        Token token = generateToken(user);
        Token savedToken = tokenRepository.save(token);
        return savedToken;
    }

    /**
     * Generate Token for login and this token will expire If we log out
     * @param user
     * @return
     */

    private Token generateToken(User user) {
        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysAgo = currentDate.plus(30, ChronoUnit.DAYS);//Token will expire after 30 days from today.
        Date expiryDate = Date.from(thirtyDaysAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
        //128 Character alphanumeric
        Token token = new Token();
        token.setExpiry(expiryDate);
        token.setValue(RandomStringUtils.randomAlphabetic(128));
        token.setUser(user);
        return token;

    }

    /**
     * Logout using token generated while login time and this token will mark as deleted after logout .
     * @param tokenValue
     */
    public void logOut(String tokenValue) {
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeleted(tokenValue, false);
        if (optionalToken.isEmpty()) {
            //Throws Exception
        }
        Token token = optionalToken.get();
        token.setDeleted(true);
        tokenRepository.save(token);
    }

    /**
     * Validate token --If token is correct token and active then It return the user for this token
     * If Token is incorrect or Marked as deleted then response will be OK and return response is null.
     * @param token
     * @return
     */
    public User validateToken(String token) {

        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedAndExpiryGreaterThan(token, false, new Date());
        if (optionalToken.isEmpty()) {
            return null;
        }
        return optionalToken.get().getUser();
    }
}
