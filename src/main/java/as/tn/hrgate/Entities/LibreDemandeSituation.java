package as.tn.hrgate.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LibreDemandeSituation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idLibreDemSituation;
    private Long codeSoc;
    @Temporal(TemporalType.DATE)
    private Date situationRequestdate;
    private String txtDemSit;
    private String ReponseSituation;
    private String txtReponseSit;
    private String filePath;



    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "matPers")
    private User user;
}
