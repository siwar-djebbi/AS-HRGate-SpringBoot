package as.tn.hrgate.Repository;

import as.tn.hrgate.Entities.LibreDemandeConge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeCongeRepository extends JpaRepository<LibreDemandeConge,Long> {

    List<LibreDemandeConge> findByUserMatPers(Long matPers);

}
