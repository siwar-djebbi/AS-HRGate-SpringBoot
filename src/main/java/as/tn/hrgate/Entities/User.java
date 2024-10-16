package as.tn.hrgate.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "matPers")

public class User  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long matPers;

    private String firstName ;
    private String lastName ;

    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String email;
    private String password;
    private Integer telephone;
    private Float salary;
    private boolean deleted = false;
    private boolean enabled= true;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    ///@JsonManagedReference
    private Set<LibreDemandeAutorisation> libreDemandeAutorisations = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user", orphanRemoval = true)
    //@JsonManagedReference
    //@JsonIgnoreProperties("user")
    private Set<LibreDemandeConge> libreDemandeConges = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<LibreDemandeConge> libreDemandePret = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<LibreDemandeConge> libreDemandeSituation = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy="idSender")
    private Set<Notification> NotifSender;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="idReceiver")
    private Set<Notification> NotifReceiver;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="idSenderrr")
    private Set<ChatMessage> ChatSender;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="idReceiverrr")
    private Set<ChatMessage> ChatReceiver;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "matPers"),
            inverseJoinColumns = @JoinColumn(name = "idRole"))
    private Set<Role> roless = new HashSet<>();

    public User(String firstName, String lastName, String email, String password, Set<String> role) {
    }

    public Set<Role> getRoles() {
        return roless;
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "matPers=" + matPers +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
