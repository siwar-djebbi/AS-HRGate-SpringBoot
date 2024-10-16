package as.tn.hrgate.Service;

import as.tn.hrgate.Entities.LibreDemandeAutorisation;
import as.tn.hrgate.Entities.User;
import as.tn.hrgate.Repository.DemandeAutorisationRepository;
import as.tn.hrgate.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutorisationService implements IAutorisationService{

    private final DemandeAutorisationRepository autorisationRepository;
    private final UserRepository userRepository;


    @Override
    public LibreDemandeAutorisation addOrUpdateDemandeAutorisation(LibreDemandeAutorisation L) {
        return autorisationRepository.save(L);
    }
    @Override
    public LibreDemandeAutorisation retrieveAutorisation(Long idLibreDemAuto) {
        return autorisationRepository.findById(idLibreDemAuto).orElse(null);
    }
    @Override
    public List<LibreDemandeAutorisation> retrieveAllAutos() {
        return autorisationRepository.findAll();
    }
    @Override
    public void removeAuto(Long idLibreDemAuto) {
        autorisationRepository.deleteById(idLibreDemAuto);
    }


    @Override
    @Transactional
    public LibreDemandeAutorisation assignAutoToUser(Long idLibreDemAuto, Long matPers) {
        LibreDemandeAutorisation autor = autorisationRepository.findById(idLibreDemAuto).orElse(null);
        User user = userRepository.findById(matPers).orElse(null);

        if (autor != null && user != null) {
            autor.setUser(user);
            autorisationRepository.save(autor);

            log.info("Authorization with ID {} has been assigned to user ({} {}).", idLibreDemAuto, user.getFirstName(), user.getLastName());

            return autor;
        } else {
            log.error("Failed to assign authorization with ID {} to user with ID {}. Authorization or user not found.", idLibreDemAuto, matPers);
            throw new IllegalArgumentException("Failed to assign authorization. Authorization or user not found.");
        }
    }

    @Override
    public LibreDemandeAutorisation retrieveAutorisationn(Long idLibreDemAuto) {
        return autorisationRepository.findById(idLibreDemAuto)
                .map(autorisation -> {
                    // Ensure the user is initialized
                    autorisation.getUser().getFirstName();
                    autorisation.getUser().getLastName();
                    return autorisation;
                })
                .orElse(null);
    }

}
