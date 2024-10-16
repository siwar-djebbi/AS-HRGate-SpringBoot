package as.tn.hrgate.Entities;

 import com.fasterxml.jackson.annotation.JsonProperty;
 import com.fasterxml.jackson.annotation.JsonProperty.Access;

 import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idNotification;

    @Temporal(TemporalType.DATE)
    private Date notifDate;
    private String libelle;


    private Integer compteur;

    @JsonProperty(access = Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "idReceiver")
    private User idReceiver;
    @JsonProperty(access = Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "idSender")
    private User idSender;



}
