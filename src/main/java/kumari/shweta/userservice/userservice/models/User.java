package kumari.shweta.userservice.userservice.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private Long id;
    private String name;
    private String hashedpassword;
    private String email;
    @ManyToMany
    private List<Role> roles;
    private  boolean isEmailVerified;
}
