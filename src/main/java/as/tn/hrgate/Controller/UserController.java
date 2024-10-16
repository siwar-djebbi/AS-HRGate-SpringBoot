package as.tn.hrgate.Controller;

import as.tn.hrgate.Entities.ERole;
import as.tn.hrgate.Entities.Role;
import as.tn.hrgate.Entities.User;
import as.tn.hrgate.Events.NewUserAddedEvent;
import as.tn.hrgate.Payload.Request.*;
import as.tn.hrgate.Payload.Response.CurrentUserResponse;
import as.tn.hrgate.Payload.Response.JwtResponse;
import as.tn.hrgate.Payload.Response.UserResponse;
import as.tn.hrgate.Repository.RoleRepository;
import as.tn.hrgate.Repository.UserRepository;
import as.tn.hrgate.Service.UserDetailsServiceImpl;
import as.tn.hrgate.secConfig.Jwt.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/User")
@Slf4j

public class UserController {
    @Value("${asserter.default.secret}")
    private String DEFAULT_PASSWORD;
    private final UserDetailsServiceImpl userService;
    private final ApplicationEventPublisher publisher;
    private final UserRepository userRepo;
@Autowired
    private RoleRepository roleRepository;
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String userAccess() {
        return "User Content.";
    }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }


    //DONE
    @PostMapping("/create")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DRH', 'ROLE_USER', 'ROLE_CHEF')")
    public ResponseEntity<JwtResponse> createNewUser(HttpServletRequest request,
                                                     @RequestHeader(name = "Authorization", required = false) String token,
                                                     @RequestBody @Valid UserRequest userRequest) throws Exception {
        try {
            if (token == null) {
                throw new IllegalArgumentException("Missing Authorization header");
            }
            Set<Role> roles = new HashSet<>();
            //System.out.println("Role :"+roles);

            // roles.add(roleRepository.findByName(ERole.ROLE_ADMIN));

            System.out.println("Role name :"+userRequest.getRole());
                roles.add(roleRepository.findByNames(userRequest.getRole()));

            System.out.println("role est "+roles);
            User newUser = userService.createNewUser(token, userRequest);
            if (newUser != null) {
                publisher.publishEvent(new NewUserAddedEvent(applicationUrl(request), newUser, DEFAULT_PASSWORD));
                return ResponseEntity.ok(new JwtResponse(token,null,newUser.getMatPers(), newUser.getFirstName(), newUser.getLastName(),newUser.getEmail(),roles));


            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            log.error("Error creating user", e);
            throw new Exception("Failed to create new user", e);
        }
    }

    //DONE
    @GetMapping("/alll")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_CRM_ADMIN', 'ROLE_PROJECT_ADMIN', 'ROLE_PRODUCT_ADMIN')")
    public ResponseEntity<List<UserResponse>> getUsers(@RequestHeader(name = "Authorization") String token) {
        try {
            List<User> users = userService.getAlluser();
            List<UserResponse> userResponses = users.stream()
                    .map(user -> new UserResponse(user.getMatPers(), user.getFirstName(), user.getLastName(), user.getEmail(),user.getTelephone()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(userResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


//DONE
    @GetMapping("/getCurrent")
    public ResponseEntity<?> getCurrentUserInfos(@RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            System.out.println("Token is: " + token);
            String actualToken = token.split(" ")[1].trim();
            CurrentUserResponse user = userService.getCurrentUserInfos(actualToken);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            throw new Exception("Failed to fetch current user information", e);
        }
    }

////DONE
    @PutMapping("/editPassword")
    public ResponseEntity<?> editPassword(@RequestHeader("Authorization") String token, @RequestBody EditPasswordRequest request) throws Exception {
        try {
            String result = userService.editPassword(token, request);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            throw new Exception(e);
        }
    }
   //DONE
    @PostMapping("/search")
    // @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_CRM_ADMIN', 'ROLE_PROJECT_ADMIN', 'ROLE_PRODUCT_ADMIN')")
    public ResponseEntity<?> searchUsers(@RequestHeader(name = "Authorization") String token, @RequestBody SearchRequest searchRequest) throws Exception {

        try {

            List<UserResponse> users = userService.searchUsers(token, searchRequest);
            return ResponseEntity.ok(users);


        }catch (Exception e) {
            throw new Exception(e);
        }
    }
//DONE DELETE USERS
    @DeleteMapping("/delete")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_CRM_ADMIN', 'ROLE_PROJECT_ADMIN', 'ROLE_PRODUCT_ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestBody @Valid DeleteUsersRequest deleteUsersRequest) throws Exception {

        try {
            userService.deleteUsers(deleteUsersRequest);

            return ResponseEntity.ok("success deleting users");
        }catch (Exception e) {
            throw new Exception(e);
        }

    }
//DONE
//@PutMapping("/editCurrent")
//public ResponseEntity<?> editCurrent(
//        @RequestHeader(name = "Authorization") String token,
//        @RequestBody ProfileEditRequest request) {
//    try {
//        // Remove the "Bearer " prefix from the token if it's included
//        String cleanToken = token.startsWith("Bearer ") ? token.substring(7) : token;
//
//        // Call the service method to edit user info
//        CurrentUserResponse user = userService.editUserProfile(cleanToken, request);
//
//        // Return the updated user info
//        return ResponseEntity.ok(user);
//    } catch (Exception e) {
//        // Log the error and return a meaningful message to the client
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user: " + e.getMessage());
//    }
//}

    //DONE
    @PostMapping("/ban")
    public ResponseEntity<String> banUserByEmail(@RequestParam("email") String email) {
        try {
            userService.banUserByEmail(email);

            return ResponseEntity.ok("User with email " + email + " has been banned.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to ban user: " + e.getMessage());
        }
    }


//DONE
    @GetMapping("/stats/banned-users")
    public ResponseEntity<Long> getBannedUsersStats() {
        long totalBannedUsers = userService.getTotalBannedUsers();

        return ResponseEntity.ok(totalBannedUsers);
    }

    private String applicationUrl(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }


    /////
    @PutMapping("/editProfile")
    public ResponseEntity<?> editProfile(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody @Valid ProfileEditRequest profileEditRequest) throws Exception {
        try {
            // Extract the token, removing the "Bearer " prefix if it exists
            String actualToken = token.startsWith("Bearer ") ? token.substring(7).trim() : token;
            log.debug("Received token: {}", token);

            // Call the user service to update the profile
            User updatedUser = userService.editUserProfile(actualToken, profileEditRequest);

            // Return the updated user profile
            return ResponseEntity.ok(new CurrentUserResponse(
                    updatedUser.getMatPers(),
                    updatedUser.getFirstName(),
                    updatedUser.getLastName(),
                    updatedUser.getEmail(),
                    updatedUser.getTelephone(),
                    updatedUser.getBirthDate()
            ));

        } catch (Exception e) {
            log.error("Error editing profile", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to edit profile: " + e.getMessage());
        }
    }


    @GetMapping("/user/{matPers}")
    public ResponseEntity<User> getUserByMatPers(@PathVariable Long matPers) {
        User user = userService.getUserByMatPers(matPers);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}




