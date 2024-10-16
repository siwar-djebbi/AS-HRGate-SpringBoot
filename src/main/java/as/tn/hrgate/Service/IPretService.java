package as.tn.hrgate.Service;

import as.tn.hrgate.Entities.LibreDemandePret;

import java.util.List;

public interface IPretService {
    LibreDemandePret addOrUpdateDemandePret(LibreDemandePret demandePret);

    LibreDemandePret retrievePret(Long idLibreDemPret);

    List<LibreDemandePret> retrieveAllPrets();

    void removePret(Long idLibreDemPret);
}
