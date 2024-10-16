package as.tn.hrgate.Entities;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LibreDemandePret {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idLibreDemPret;
    private Long codeSoc;
    @Temporal(TemporalType.DATE)
    private Date pretRequestdate;
    private Integer codeGrpPret;
    private String typePret;
    private Float montantDem;
    private String txtDemPret;
    private String txtRepPret;
    private String txtRepPretDRH;

    private String categoriePret;
    private String nomBanque;
    private Long numCompte;


    @ManyToOne
    @JoinColumn(name = "matPers")
    private User user;
}
