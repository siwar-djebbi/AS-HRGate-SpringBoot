package as.tn.hrgate.Controller;

import as.tn.hrgate.Entities.LibreDemandeConge;
import as.tn.hrgate.Entities.LibreDemandePret;
import as.tn.hrgate.Service.ICongeService;
import as.tn.hrgate.Service.IPretService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Pret")
@Slf4j
public class LibreDemandePretController {

    private final IPretService pretService;

    @PostMapping("/addPretRequest")
    LibreDemandePret addPretRequest(@RequestBody LibreDemandePret pret)
    {
        return pretService.addOrUpdateDemandePret(pret);
    }
    @PutMapping("/updatePretRequest")
    LibreDemandePret updatePretRequest(@RequestBody LibreDemandePret pret)
    {
        return pretService.addOrUpdateDemandePret(pret);
    }
    @GetMapping("/get/{idLibreDemPret}")
    LibreDemandePret getLibreDemPret(@PathVariable("idLibreDemPret") Long idLibreDemPret)
    {
        return pretService.retrievePret(idLibreDemPret);
    }
    @GetMapping("/allDemandesPrets")
    List<LibreDemandePret> getAllPrets() { return pretService.retrieveAllPrets();}
    @DeleteMapping("/delete/{idLibreDemPret}")
    void deleteDemPret(@PathVariable("idLibreDemPret") Long idLibreDemPret)
    {
        pretService.removePret(idLibreDemPret);
    }
}
