package as.tn.hrgate.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@Setter
@ToString
//@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LibreDemandeAutorisation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idLibreDemAuto;
    private Long codeSoc;
    @Temporal(TemporalType.DATE)
    private Date autoRequestdate;
    @Temporal(TemporalType.DATE)
    private Date autoStartdate;
    @Temporal(TemporalType.DATE)
    private Date autoEnddate;
    private  Integer autoStarthour;
    private  Integer autoStartmin;

    private  Integer autoEndhour;
    private  Integer autoEndmin;

    private String autoDuration;

    private String textDemande;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(name = "matPers")
    private User user;


    @Override
    public String toString() {
        return "LibreDemandeAutorisation{" +
                "idLibreDemAuto=" + idLibreDemAuto +
                ", codeSoc=" + codeSoc +
                ", autoRequestdate=" + autoRequestdate +
                ", autoStartdate=" + autoStartdate +
                ", autoEnddate=" + autoEnddate +
                ", autoStarthour=" + autoStarthour +
                ", autoStartmin=" + autoStartmin +
                ", autoEndhour=" + autoEndhour +
                ", autoEndmin=" + autoEndmin +
                ", autoDuration='" + autoDuration + '\'' +
                ", textDemande='" + textDemande + '\'' +
                ", user=" + (user != null ? user.getMatPers() : "null") +
                '}';
    }

    public User getUser() {
        return user;
    }


    public LibreDemandeAutorisation(Long idLibreDemAuto, Long codeSoc, Date autoRequestdate, Date autoStartdate, Date autoEnddate, Integer autoStarthour, Integer autoStartmin, Integer autoEndhour, Integer autoEndmin, String autoDuration, String textDemande, User user) {
        this.idLibreDemAuto = idLibreDemAuto;
        this.codeSoc = codeSoc;
        this.autoRequestdate = autoRequestdate;
        this.autoStartdate = autoStartdate;
        this.autoEnddate = autoEnddate;
        this.autoStarthour = autoStarthour;
        this.autoStartmin = autoStartmin;
        this.autoEndhour = autoEndhour;
        this.autoEndmin = autoEndmin;
        this.autoDuration = autoDuration;
        this.textDemande = textDemande;
        this.user = user;
    }
}
