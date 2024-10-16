package as.tn.hrgate.Repository;

import as.tn.hrgate.Entities.LibreDemandeAutorisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeAutorisationRepository extends JpaRepository<LibreDemandeAutorisation,Long> {
}
