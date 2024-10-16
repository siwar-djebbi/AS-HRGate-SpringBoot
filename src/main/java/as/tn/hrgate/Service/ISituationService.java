package as.tn.hrgate.Service;

import as.tn.hrgate.Entities.LibreDemandeSituation;

import java.util.List;

public interface ISituationService {
    LibreDemandeSituation addOrUpdateDemandeSituation(LibreDemandeSituation demandeSituation);

    LibreDemandeSituation retrieveSituation(Long idLibreDemSituation);

    List<LibreDemandeSituation> retrieveAllSituations();

    void removeSituation(Long idLibreDemSituation);

    LibreDemandeSituation getSituationByEmail(String email);
}
