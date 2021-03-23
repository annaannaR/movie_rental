package pl.ampv.registration.dto;

import lombok.*;
import pl.ampv.registration.model.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserData implements Serializable {

    @NotEmpty(message = "First name can not be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name can not be empty")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Please provide a valid email id")
    private String email;

    @NotEmpty(message = "Password can not be empty")
    private String password;

    public User getUserFromDto(){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
