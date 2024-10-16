package as.tn.hrgate.Service;

import as.tn.hrgate.Entities.User;
import as.tn.hrgate.Payload.Response.CurrentUserResponse;

import java.util.List;

public interface IUserServ {
    List<User> getAlluser();

    CurrentUserResponse getCurrentUserInfos(String token) throws Exception;
}
