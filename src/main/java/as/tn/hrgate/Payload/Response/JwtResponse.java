package as.tn.hrgate.Payload.Response;

import as.tn.hrgate.Entities.Role;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;
    private String type = "Bearer";
    private Long matPers;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Role> roles;



    public JwtResponse(String token, String type, Long matPers, String firstName, String lastName, String email, Set<Role> roles) {
        this.token = token;
        this.type = type;
        this.matPers = matPers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(String token, String firstName, String lastName) {
        this.token = token;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //public JwtResponse(String token, Long matPers, String firstName, String lastName, String email, List<String> roles) {
       // this.token = token;
      //  this.matPers = matPers;
      //  this.firstName = firstName;
      //  this.lastName = lastName;
      //  this.email = email;
      //  this.roles = roles;
   // }
    public JwtResponse(String token, Long matPers, String firstName, String lastName, String email, Set<Role> roles) {
        this.token = token;
        this.matPers = matPers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMatPers(Long matPers) {
        this.matPers = matPers;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public Long getMatPers() {
        return matPers;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public JwtResponse(String token) {
        this.token = token;
    }
}
