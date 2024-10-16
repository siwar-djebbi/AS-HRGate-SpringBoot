package as.tn.hrgate.Service;


import as.tn.hrgate.Entities.ERole;
import as.tn.hrgate.Entities.LibreDemandeConge;
import as.tn.hrgate.Entities.Notification;
import as.tn.hrgate.Entities.User;
import as.tn.hrgate.Repository.NotificationRepository;
import as.tn.hrgate.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private UserDetailsServiceImpl userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotification(String message) {
        template.convertAndSend("/topic/notifications", message);
    }
    public void sendNotificationToUser(String firstName, String message) {
        template.convertAndSend("/user/" + firstName + "/topic/notifications", message);
    }




//    public void sendNotificationToChefs(LibreDemandeConge conge) {
//        List<User> chefs = userService.getUsersByRole("ROLE_CHEF");
//
////        String message = String.format(
////                "New leave request from %s: %s (Request Date: %s)",
////                conge.getUser().getFirstName(),
////                conge.getTxtDem(),
////                conge.getCongeRequestdate()
////        );
//
//            template.convertAndSend("/topic/notifications", conge);
//
//    }

    public void sendNotificationToChefs(LibreDemandeConge conge) {
        List<User> chefs = userRepository.findByRolesName(ERole.ROLE_CHEF);
        System.out.println("Sending notifications to chefs: " + chefs.size());
        for (User chef : chefs) {
            Notification notification = new Notification();
            notification.setIdReceiver(chef);
            notification.setNotifDate(new Date());
            notification.setLibelle("New leave request from " + conge.getUser().getFirstName() + " " + conge.getUser().getLastName() +
                    " for the period " + conge.getCongeStartdate() + " to " + conge.getCongeEnddate());
            notificationRepository.save(notification);
            System.out.println("Sending notification to user: " + chef.getFirstName());
            template.convertAndSend("/user/" + chef.getFirstName() + "/topic/notifications", notification);
        }
    }



    public void sendOne(Notification notification) {
        Integer compteur = this.notificationRepository.maxCompteur(notification.getIdReceiver().getMatPers());
        if (compteur != null) {
            notification.setCompteur(compteur + 1);
        } else {
            notification.setCompteur(1);
        }

        this.notificationRepository.save(notification);
        template.convertAndSend("/topic/notification", notification);
    }




    public List<Notification> findByMatPers(Long matPers) {
        return notificationRepository.findByIdReceiverMatPers(matPers);
    }
}
