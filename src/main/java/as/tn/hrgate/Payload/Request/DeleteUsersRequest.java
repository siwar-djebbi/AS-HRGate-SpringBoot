package as.tn.hrgate.Payload.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DeleteUsersRequest {
    @NotNull(message = "should at least contain one element")
    private List<String> emails = new ArrayList<>();
}