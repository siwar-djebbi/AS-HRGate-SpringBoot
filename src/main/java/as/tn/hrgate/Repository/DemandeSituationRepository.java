package as.tn.hrgate.Repository;

import as.tn.hrgate.Entities.LibreDemandeSituation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DemandeSituationRepository extends JpaRepository<LibreDemandeSituation,Long> {
    LibreDemandeSituation findByUserEmail(String email);
    @Query("SELECT s FROM LibreDemandeSituation s JOIN FETCH s.user WHERE s.user.matPers = :matPers")
    Optional<LibreDemandeSituation> findByMatPersWithUser(@Param("matPers") Long matPers);

}
