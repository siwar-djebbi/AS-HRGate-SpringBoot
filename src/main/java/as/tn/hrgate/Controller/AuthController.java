package as.tn.hrgate.Controller;

import java.util.*;
import java.util.stream.Collectors;

import as.tn.hrgate.Service.EmailService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import as.tn.hrgate.Entities.ERole;
import as.tn.hrgate.Entities.Role;
import as.tn.hrgate.Entities.User;
import as.tn.hrgate.Payload.Request.LoginRequest;
import as.tn.hrgate.Payload.Request.SignUpRequest;
import as.tn.hrgate.Payload.Response.JwtResponse;
import as.tn.hrgate.Payload.Response.MessageResponse;
import as.tn.hrgate.Repository.RoleRepository;
import as.tn.hrgate.Repository.UserRepository;
import as.tn.hrgate.secConfig.Jwt.JwtUtils;
import as.tn.hrgate.Service.UserDetailsImpl;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

   @Autowired
   PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private EmailService emailService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
       Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
            String jwt = jwtUtils.generateJwtToken(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        //Set<Role> roles = new HashSet<>();
        // Convert List<String> to Set<Role>
        Set<Role> roles = userDetails.getAuthorities().stream()
                .map(item -> {
                    Role role = roleRepository.findByName(ERole.valueOf(item.getAuthority()));
                    if (role == null) {
                        throw new RuntimeException("Error: Role not found.");
                    }
                    return role;
                })
                .collect(Collectors.toSet());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getMatPers(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getEmail(),
                roles
        ));


//        ResponseEntity<JwtResponse> b = null;
//        String jwt="";
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//             jwt = jwtUtils.generateJwtToken(authentication);
//            System.out.println("token :"+jwt);
//            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//
//
//        } catch (BadCredentialsException e) {
//
//
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized error: Bad credentials");
//        }
//
//        return ResponseEntity.ok(new JwtResponse(jwt));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        String token = UUID.randomUUID().toString();

        User user = new User(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Optional<Role> userRoleOptional = Optional.ofNullable(roleRepository.findByName(ERole.ROLE_USER));
            Role userRole = userRoleOptional.orElseThrow(() -> new RuntimeException("Error: Default role (ROLE_USER) is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Optional<Role> adminRoleOptional = Optional.ofNullable(roleRepository.findByName(ERole.ROLE_ADMIN));
                        Role adminRole = adminRoleOptional.orElseThrow(() -> new RuntimeException("Error: Role ROLE_ADMIN is not found."));
                        roles.add(adminRole);
                        break;
                    case "chef":
                        Optional<Role> chefRoleOptional = Optional.ofNullable(roleRepository.findByName(ERole.ROLE_CHEF));
                        Role chefRole = chefRoleOptional.orElseThrow(() -> new RuntimeException("Error: Role ROLE_CHEF is not found."));
                        roles.add(chefRole);
                        break;
                    case "drh":
                        Optional<Role> drhRoleOptional = Optional.ofNullable(roleRepository.findByName(ERole.ROLE_DRH));
                        Role drhRole = drhRoleOptional.orElseThrow(() -> new RuntimeException("Error: Role ROLE_DRH is not found."));
                        roles.add(drhRole);
                        break;
                    default:
                        throw new RuntimeException("Error: Role " + role + " is not recognized.");
                }
            });
        }

        user.setRoless(roles);
        userRepository.save(user);

        emailService.sendConfirmationEmail(signUpRequest.getEmail(), token);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
//    @GetMapping("/confirm")
//    public ResponseEntity<?> confirmUser(@RequestParam("token") String token) {
//        Optional<User> userOptional = userRepository.findByToken(token);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            user.setEnabled(true); // or however you want to handle confirmation
//            userRepository.save(user);
//            return ResponseEntity.ok(new MessageResponse("User confirmed successfully!"));
//        }
//        return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid token."));
//    }

}
