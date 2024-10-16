package as.tn.hrgate.Service;

import as.tn.hrgate.Entities.LibreDemandeSituation;
import as.tn.hrgate.Repository.DemandeSituationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SituationService implements ISituationService {
    private final DemandeSituationRepository situationRepository;

    @Override
    public LibreDemandeSituation addOrUpdateDemandeSituation(LibreDemandeSituation demandeSituation) {
        return situationRepository.save(demandeSituation);
    }
    @Override
    public LibreDemandeSituation retrieveSituation(Long idLibreDemSituation) {
        return situationRepository.findById(idLibreDemSituation).orElse(null);
    }
    @Override
    public List<LibreDemandeSituation> retrieveAllSituations() {
        return situationRepository.findAll();
    }
    @Override
    public void removeSituation(Long idLibreDemSituation) {
        situationRepository.deleteById(idLibreDemSituation);
    }

    @Override
    public LibreDemandeSituation getSituationByEmail(String email) {
        return situationRepository.findByUserEmail(email);
    }

}
