package kumari.shweta.userservice.userservice.repositories;

import kumari.shweta.userservice.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    User save(User user);
}
