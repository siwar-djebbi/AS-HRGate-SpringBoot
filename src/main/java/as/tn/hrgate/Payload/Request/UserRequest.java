package as.tn.hrgate.Payload.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "first name is mandatory")
    private String firstName;
    @NotBlank(message = "last name is mandatory")
    private String lastName;
    @NotBlank(message = "email is mandatory")
    private String email;
    private String role = "user";
    private String password;

    public CharSequence getPassword() { return password;
    }
}
