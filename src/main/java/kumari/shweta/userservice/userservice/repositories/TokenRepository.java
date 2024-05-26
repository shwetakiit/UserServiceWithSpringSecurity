package kumari.shweta.userservice.userservice.repositories;

import kumari.shweta.userservice.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

 // Token findByToken(String token);
 @Override
 Token save(Token token);

 Optional<Token> findByValueAndDeleted(String tokenValue, boolean isDeleted);

 /**
  * Select * from token where value=tokenValue  and deleted=false and expiry_date>time.
  */
 Optional<Token> findByValueAndDeletedAndExpiryGreaterThan(String token, boolean isDeleted, Date currentDate);
}
