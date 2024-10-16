package as.tn.hrgate.Service;

import as.tn.hrgate.Entities.LibreDemandeConge;
import as.tn.hrgate.Entities.User;
import as.tn.hrgate.Repository.DemandeCongeRepository;
import as.tn.hrgate.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CongeService implements ICongeService {
    private final UserRepository userRepository;
    private final DemandeCongeRepository congeRepository;

    @Override
    public LibreDemandeConge addOrUpdateDemandeConge(LibreDemandeConge demandeConge) {
        return congeRepository.save(demandeConge);
    }
    @Override
    public Object retrieveConge(Long idLibreDemConge) {
        return congeRepository.findById(idLibreDemConge).orElse(null);
    }
    @Override
    public List<LibreDemandeConge> retrieveAllConges() {
        return congeRepository.findAll();
    }
    @Override
    public void removeConge(Long idLibreDemConge) {
        congeRepository.deleteById(idLibreDemConge);
    }

    @Override
    public int calculateLeaveDurationInDays(Date congeStartdate, Date congeEnddate) {
        if (congeStartdate == null || congeEnddate == null) {
            throw new IllegalArgumentException("Leave start date or end date cannot be null.");
        }

        long startTime = congeStartdate.getTime();
        long endTime = congeEnddate.getTime();

        long durationInMillis = endTime - startTime;
        int durationInDays = (int) (durationInMillis / (24 * 60 * 60 * 1000));

        return durationInDays;
    }

    @Override
    @Transactional
    public LibreDemandeConge acceptLeaveRequest(Long idLibreDemConge) {
        try {
            LibreDemandeConge conge = congeRepository.findById(idLibreDemConge).orElse(null);

            if (conge == null) {
                throw new EntityNotFoundException("Leave not found with ID: " + idLibreDemConge);
            }
            int leaveDurationInDays = calculateLeaveDurationInDays(conge.getCongeStartdate(), conge.getCongeEnddate());
            if (conge.getCongeDaysleft() < leaveDurationInDays) {
                throw new IllegalArgumentException("Leave duration exceeds available leave days left.");
            }
            conge.setValide("V");

            return congeRepository.save(conge);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to accept leave request.", e);
        }
    }

    @Override
    public LibreDemandeConge refuseLeaveRequest(Long idLibreDemConge) {
        LibreDemandeConge conge = congeRepository.findById(idLibreDemConge)
                .orElseThrow(() -> new EntityNotFoundException("Leave not found with ID: " + idLibreDemConge));

        int leaveDurationInDays = calculateLeaveDurationInDays(conge.getCongeStartdate(), conge.getCongeEnddate());
        if (conge.getCongeDaysleft()< leaveDurationInDays) {
            throw new IllegalArgumentException("Leave duration exceeds available leave days left.");
        }
        conge.setValide("N");

        return congeRepository.save(conge);
    }
    @Override
    public List<LibreDemandeConge> getLeavesByUser(Long matPers) {
        return congeRepository.findByUserMatPers(matPers);
    }


    @Override
    @Transactional
    public LibreDemandeConge assignLeaveToUser(Long idLibreDemConge, Long matPers) {
        LibreDemandeConge conge = congeRepository.findById(idLibreDemConge).orElse(null);
        User user = userRepository.findById(matPers).orElse(null);

        if (conge != null && user != null) {
            conge.setUser(user);
            congeRepository.save(conge);

            log.info("Leave with ID {} has been assigned to user ({} {}).", idLibreDemConge, user.getFirstName(), user.getLastName());

            return conge;
        } else {
            log.error("Failed to assign leave with ID {} to user with ID {}. Leave or user not found.", idLibreDemConge, matPers);
            throw new IllegalArgumentException("Failed to assign leave. Leave or user not found.");
        }
    }

    @Override
    public List<LibreDemandeConge> getLeaveRequestsByMatPers(Long matPers) {
        return congeRepository.findByUserMatPers(matPers);
    }
}
