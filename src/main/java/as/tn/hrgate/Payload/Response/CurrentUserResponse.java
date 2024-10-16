package as.tn.hrgate.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class CurrentUserResponse {
    private Long matPers;
    private String firstName;
    private String lastName;
    private String email;
    private Integer telephone;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private Float salary;
    private List<String> role;


    public CurrentUserResponse(Long matPers, String firstName, String lastName, String email, List<String> role) {
        this.matPers = matPers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public CurrentUserResponse(Long matPers, String firstName, String lastName, String email) {
        this.matPers = matPers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public CurrentUserResponse(Long matPers, String firstName, String lastName, String email, Integer telephone, Date birthDate, Float salary) {
        this.matPers = matPers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    public CurrentUserResponse(Long matPers, String firstName, String lastName, String email, Integer telephone, Date birthDate) {
        this.matPers = matPers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.birthDate = birthDate;
    }
}
