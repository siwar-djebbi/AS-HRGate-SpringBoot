package as.tn.hrgate.Controller;

import as.tn.hrgate.Entities.LibreDemandeAutorisation;
import as.tn.hrgate.Service.IAutorisationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequiredArgsConstructor
@RequestMapping("/Autorisation")
@Slf4j
public class LibreDemandeAutorisationController {
    private final IAutorisationService autorisationService;


    @PostMapping("/addAutoRequest")
    public ResponseEntity<LibreDemandeAutorisation> addAutoRequest(@RequestBody LibreDemandeAutorisation autorisation) {
        try {
            LibreDemandeAutorisation savedAutorisation = autorisationService.addOrUpdateDemandeAutorisation(autorisation);
            return ResponseEntity.ok(savedAutorisation);
        } catch (Exception e) {
            log.error("Error adding authorization request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/updateAutoRequest")
    LibreDemandeAutorisation updateAutoRequest(@RequestBody LibreDemandeAutorisation autorisation)
    {
        return autorisationService.addOrUpdateDemandeAutorisation(autorisation);
    }
    @GetMapping("/get/{idLibreDemAuto}")
    LibreDemandeAutorisation getLibreDemAuto(@PathVariable("idLibreDemAuto") Long idLibreDemAuto)
    {
        return autorisationService.retrieveAutorisation(idLibreDemAuto);
    }
    @GetMapping("/allDemandesAuto")
    List<LibreDemandeAutorisation> getAllAutorisations() { return autorisationService.retrieveAllAutos();}
    @DeleteMapping("/delete/{idLibreDemAuto}")
    public ResponseEntity<String> deleteDemAuto(@PathVariable("idLibreDemAuto") Long idLibreDemAuto) {
        try {
            autorisationService.removeAuto(idLibreDemAuto);
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Authorization request deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Failed to delete authorization request.");
        }
    }
    @PutMapping("/assignAutoToUser/{idLibreDemAuto}/{matPers}")
    public ResponseEntity<String> assignAutoToUser(@PathVariable("idLibreDemAuto") Long idLibreDemAuto, @PathVariable("matPers") Long matPers) {
        try {
            autorisationService.assignAutoToUser(idLibreDemAuto, matPers);
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Authorization assigned to user successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Failed to assign authorization to user.");
        }
    }

    @GetMapping("/gett/{idLibreDemAuto}")
    public ResponseEntity<LibreDemandeAutorisation> getLibreDemAutoo(@PathVariable("idLibreDemAuto") Long idLibreDemAuto) {
        LibreDemandeAutorisation autorisation = autorisationService.retrieveAutorisationn(idLibreDemAuto);
        if (autorisation != null) {
            // Log the retrieved data
            System.out.println("Retrieved Autorisation: " + autorisation);
            System.out.println("User associated with Autorisation: " + autorisation.getUser().getFirstName()+ " " +autorisation.getUser().getLastName());
            return ResponseEntity.ok(autorisation);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
