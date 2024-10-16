package as.tn.hrgate.Service;

import as.tn.hrgate.Entities.LibreDemandeAutorisation;
import as.tn.hrgate.Entities.LibreDemandeConge;

import javax.transaction.Transactional;
import java.util.List;

public interface IAutorisationService {

    LibreDemandeAutorisation addOrUpdateDemandeAutorisation(LibreDemandeAutorisation L);

    LibreDemandeAutorisation retrieveAutorisation(Long idLibreDemAuto);

    List<LibreDemandeAutorisation> retrieveAllAutos();

    void removeAuto(Long idLibreDemAuto);

    @Transactional
    LibreDemandeAutorisation assignAutoToUser(Long idLibreDemAuto, Long matPers);


    LibreDemandeAutorisation retrieveAutorisationn(Long idLibreDemAuto);
}
