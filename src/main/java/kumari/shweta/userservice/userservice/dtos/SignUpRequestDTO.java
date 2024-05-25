package kumari.shweta.userservice.userservice.dtos;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SignUpRequestDTO {
    private String email;
    private String name;
    private String password;

}
