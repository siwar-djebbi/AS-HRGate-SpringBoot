package as.tn.hrgate.Payload.Request;

import lombok.Data;

@Data
public class CurrentUserRequest {
    private String currentPassword;
    private String firstName;
    private String lastName;
    private String email;


}