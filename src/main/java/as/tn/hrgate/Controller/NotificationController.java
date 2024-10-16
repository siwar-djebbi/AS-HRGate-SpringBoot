package as.tn.hrgate.Controller;

import as.tn.hrgate.Entities.Notification;
import as.tn.hrgate.Entities.User;
import as.tn.hrgate.Repository.NotificationRepository;
import as.tn.hrgate.Repository.UserRepository;
import as.tn.hrgate.Service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/notification")
@Slf4j
public class NotificationController {
    @MessageMapping("/send")
    @SendTo("/topic/notifications")
    public String sendNotification(String message) {
        return message;
    }

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/sendTest")
    @SendTo("/topic/notification")
    public Notification sendTestNotification(@RequestBody Notification message) {

            System.out.println("Notification test");
            try {
                notificationService.sendOne(message);
            }catch(Exception e) {
                System.out.println(e);

            }

        return     message;

    }

    @GetMapping("/sendTestNotification")
    public ResponseEntity<String> sendTestNotification() {
        User sender = userRepository.findByFirstName("Mich"); // Replace with actual sender's identifier
        User receiver = userRepository.findByFirstName("ras");

        System.out.println("Test notification"+sender+"  "+receiver);

        Notification notification = new Notification();
        notification.setLibelle("Test notification");
        notification.setNotifDate(new Date());
        notification.setIdReceiver(sender);
        notification.setIdSender(receiver);
        // Assuming there is a default user for testing
        User testUser = new User();
        testUser.setFirstName("testUser");
        notification.setIdReceiver(testUser);
        notificationService.sendOne(notification);
        return ResponseEntity.ok("Test notification sent");
    }


    @GetMapping("/getNotificationsByMatPers/{matPers}")
    public ResponseEntity<List<Notification>> getNotificationsByMatPers(@PathVariable Long matPers) {
        try {
            List<Notification> notifications = notificationService.findByMatPers(matPers);
            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            log.error("Error fetching notifications for matPers: {}", matPers, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/getMax/{matPers}")
    public Integer getMax(@PathVariable Long matPers) {
        try {
            Integer notifications = notificationRepository.maxCompteur(matPers);
            return notifications;
        } catch (Exception e) {
            log.error("Error fetching notifications for matPers: {}", matPers, e);
            return 1;
        }
    }
}

