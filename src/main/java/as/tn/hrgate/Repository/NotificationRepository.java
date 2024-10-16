package as.tn.hrgate.Repository;

import as.tn.hrgate.Entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Query("SELECT n FROM Notification n WHERE n.idReceiver.matPers = :matPers")
    List<Notification> findByIdReceiverMatPers(@Param("matPers") Long matPers);

    @Query(value = "SELECT count(n.compteur) FROM Notification n WHERE n.id_receiver = :matPers",nativeQuery = true)
    Integer maxCompteur(@Param("matPers") Long matPers);
}
