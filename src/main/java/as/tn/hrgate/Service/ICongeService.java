package as.tn.hrgate.Service;

import as.tn.hrgate.Entities.LibreDemandeConge;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface ICongeService {
    LibreDemandeConge addOrUpdateDemandeConge(LibreDemandeConge demandeConge);

    Object retrieveConge(Long idLibreDemConge);

    List<LibreDemandeConge> retrieveAllConges();

    void removeConge(Long idLibreDemConge);

    int calculateLeaveDurationInDays(Date congeStartdate, Date congeEnddate);

    @Transactional
    LibreDemandeConge acceptLeaveRequest(Long idLibreDemConge);

    LibreDemandeConge refuseLeaveRequest(Long idLibreDemConge);

    List<LibreDemandeConge> getLeavesByUser(Long matPers);

    @Transactional
    LibreDemandeConge assignLeaveToUser(Long idLibreDemConge, Long matPers);

    List<LibreDemandeConge> getLeaveRequestsByMatPers(Long matPers);
}
