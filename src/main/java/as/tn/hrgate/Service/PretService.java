package as.tn.hrgate.Service;

import as.tn.hrgate.Entities.LibreDemandePret;
import as.tn.hrgate.Repository.DemandePretRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PretService implements IPretService {
    private final DemandePretRepository pretRepository;
    @Override
    public LibreDemandePret addOrUpdateDemandePret(LibreDemandePret demandePret) {
        return pretRepository.save(demandePret);
    }
    @Override
    public LibreDemandePret retrievePret(Long idLibreDemPret) {
        return pretRepository.findById(idLibreDemPret).orElse(null);
    }
    @Override
    public List<LibreDemandePret> retrieveAllPrets() {
        return pretRepository.findAll();
    }
    @Override
    public void removePret(Long idLibreDemPret) {
        pretRepository.deleteById(idLibreDemPret);
    }

}
