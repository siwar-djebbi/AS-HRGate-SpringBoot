package as.tn.hrgate.Controller;

import as.tn.hrgate.Entities.LibreDemandeAutorisation;
import as.tn.hrgate.Entities.LibreDemandeConge;
import as.tn.hrgate.Entities.User;
import as.tn.hrgate.Payload.Response.CurrentUserResponse;
import as.tn.hrgate.Service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequiredArgsConstructor
@RequestMapping("/Conge")
@Slf4j
public class LibreDemandeCongeController {

    private NotificationService notificationService;

    private final ICongeService congeService;
    private final UserDetailsServiceImpl userService;

    @PostMapping("/addCongeRequest")
    LibreDemandeConge addCongeRequest(@RequestBody LibreDemandeConge conge)
    {
        return congeService.addOrUpdateDemandeConge(conge);
    }
    @PutMapping("/updateCongeRequest")
    LibreDemandeConge updateCongeRequest(@RequestBody LibreDemandeConge conge)
    {
        return congeService.addOrUpdateDemandeConge(conge);
    }
    @GetMapping("/get/{idLibreDemConge}")
    Object getLibreDemConge(@PathVariable("idLibreDemConge") Long idLibreDemConge)
    {
        return congeService.retrieveConge(idLibreDemConge);

    }
    @GetMapping("/allDemandesConges")
    List<LibreDemandeConge> getAllConges() { return congeService.retrieveAllConges();}
    @DeleteMapping("/delete/{idLibreDemConge}")
    void deleteDemConge(@PathVariable("idLibreDemConge") Long idLibreDemConge)
    {
        congeService.removeConge(idLibreDemConge);
    }


    @PostMapping("/addCongeRequesttt")

    public ResponseEntity<?> addCongeRequesttt(@RequestHeader(name = "Authorization") String token, @RequestBody LibreDemandeConge conge) {
        try {
            String actualToken = token.split(" ")[1].trim();
            CurrentUserResponse currentUserResponse = userService.getCurrentUserInfos(actualToken);
            User currentUser = convertToUser(currentUserResponse);
            conge.setUser(currentUser);

            if (conge.getValide() == null) {
                conge.setValide("I");
            }

            LibreDemandeConge savedConge = congeService.addOrUpdateDemandeConge(conge);
            notificationService.sendNotificationToChefs(savedConge);

            ///notificationService.sendNotificationToChefs(savedConge);
            return ResponseEntity.ok(savedConge);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add leave request");
        }
    }


    private User convertToUser(CurrentUserResponse currentUserResponse) {
        User user = new User();
        user.setMatPers(currentUserResponse.getMatPers());
        user.setFirstName(currentUserResponse.getFirstName());
        user.setLastName(currentUserResponse.getLastName());
        user.setEmail(currentUserResponse.getEmail());
        // Add other properties as needed
        return user;
    }


    @GetMapping("/congeduration/{startDate}/{endDate}")
    public int calculateLeaveDurationInDays(@PathVariable String startDate, @PathVariable String endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);
            return congeService.calculateLeaveDurationInDays(start, end);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd format.");
        }
    }

    @PutMapping("/accept/{idLibreDemConge}")
    public ResponseEntity<LibreDemandeConge> acceptLeaveRequest(@PathVariable Long idLibreDemConge) {
        try {
            LibreDemandeConge acceptedConge = congeService.acceptLeaveRequest(idLibreDemConge);
            return ResponseEntity.ok(acceptedConge);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("/{idLibreDemConge}/refuse")
    public ResponseEntity<Map<String, String>> refuseLeaveRequest(@PathVariable("idLibreDemConge") Long idLibreDemConge) {
        try {
            congeService.refuseLeaveRequest(idLibreDemConge);
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Leave request refused successfully.");
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to refuse leave request: " + e.getMessage()));
        }
    }
    @GetMapping("/user/{matPers}")
    public List<LibreDemandeConge> getLeavesByUser(@PathVariable Long matPers) {
        return congeService.getLeavesByUser(matPers);
    }

    @PutMapping("/assignLeaveToUser/{idLibreDemConge}/{matPers}")
    public ResponseEntity<String> assignLeaveToUser(@PathVariable("idLibreDemConge") Long idLibreDemConge, @PathVariable("matPers") Long matPers) {
        try {
            congeService.assignLeaveToUser(idLibreDemConge, matPers);
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Leave assigned to user successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Failed to assign leave to user.");
        }
    }

    @GetMapping("/getLeaveRequestsByMatPers/{matPers}")
    public ResponseEntity<List<LibreDemandeConge>> getLeaveRequestsByMatPers(@PathVariable Long matPers) {
        List<LibreDemandeConge> leaveRequests = congeService.getLeaveRequestsByMatPers(matPers);
        return ResponseEntity.ok(leaveRequests);
    }

}
