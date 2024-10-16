package as.tn.hrgate.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idLibreDemConge")
public class LibreDemandeConge implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idLibreDemConge;
    private Long codeSoc;
    @Temporal(TemporalType.DATE)
    private Date congeRequestdate;

    @Temporal(TemporalType.DATE)
    private Date congeStartdate;
    @Temporal(TemporalType.DATE)
    private Date congeEnddate;
    private String txtDem;
    private String txtReponse;
    private String ReponseChef;
    private String txtChef;
    private String ReponseDRH;
    private Integer congeTempsDebut;
    private Integer congeTempsFin;
    private Integer codeM;

    private Integer congeDaysleft;
    private String congeType;

    @Column(nullable = false)
    private String valide = "I";



    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonBackReference
    ///@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    //@JsonIgnoreProperties("libreDemandeConges")
    @JsonIgnore
    @JoinColumn(name = "matPers")
    private User user;

}
