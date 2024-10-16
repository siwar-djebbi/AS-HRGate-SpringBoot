package as.tn.hrgate.Events;

import as.tn.hrgate.Entities.User;
import org.springframework.context.ApplicationEvent;

public class NewUserAddedEvent extends ApplicationEvent {
    private String applicationUrl;

    private String defaultPassword;

    private User user;
    public NewUserAddedEvent(String applicationUrl, User user, String defaultPassword) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
        this.defaultPassword = defaultPassword;


    }

}
