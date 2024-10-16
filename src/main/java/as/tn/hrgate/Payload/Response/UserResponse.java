package as.tn.hrgate.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long matPers;

    private String firstName;
    private String lastName;
    private String email;
    private Integer telephone;

    private List<String> role;

    public UserResponse(Long matPers, String firstName, String lastName, String email) {
    }

    public UserResponse(Long matPers, String firstName, String lastName, String email, Integer telephone) {
        this.matPers = matPers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
    }

}
