package as.tn.hrgate.Service;

import as.tn.hrgate.Entities.ERole;
import as.tn.hrgate.Entities.Role;
import as.tn.hrgate.Payload.Request.*;
import as.tn.hrgate.Payload.Response.CurrentUserResponse;
import as.tn.hrgate.Payload.Response.UserResponse;
import as.tn.hrgate.Repository.RoleRepository;
import as.tn.hrgate.secConfig.Jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import as.tn.hrgate.Entities.User;
import as.tn.hrgate.Repository.UserRepository;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
@Autowired
    UserRepository userRepository;
@Autowired
    private RoleRepository roleRepository;
@Autowired
    PasswordEncoder passwordEncoder;
@Autowired
private JwtUtils jwtUtils;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public User createNewUser(String token, UserRequest userRequest) throws Exception {
        try {
            User newUser = new User();
            Set<Role> roles = new HashSet<>();
            // roles.add(roleRepository.findByName(ERole.ROLE_ADMIN));

                roles.add(roleRepository.findByNames(userRequest.getRole()));


            newUser.setRoless(roles);
            newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            newUser.setFirstName(userRequest.getFirstName());
            newUser.setLastName(userRequest.getLastName());
            newUser.setEmail(userRequest.getEmail());
            newUser.setEnabled(true);

            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new Exception("Failed to create new user", e);
        }
    }

    public List<User> getAlluser() {
        return userRepository.findAll();
    }

////mazelt
public CurrentUserResponse getCurrentUserInfos(String token) throws Exception {
    try {
        String email = jwtUtils.getUserNameFromJwtToken(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new CurrentUserResponse(
                        user.getMatPers(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getTelephone(),
                        user.getBirthDate(),
                        user.getSalary()
                );
    } catch (Exception e) {
        throw new Exception("Failed to fetch current user information", e);
    }
}


    ////mzelt
    public String editPassword(String token, EditPasswordRequest request) throws Exception {
        try {
            if (!request.getNewPassword().trim().equals(request.getRetypedNewPassword().trim())) {
                throw new Exception("Passwords don't match");
            }
            String email = jwtUtils.getUserNameFromJwtToken(token.split(" ")[1].trim());
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
            if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
                throw new Exception("Incorrect password");
            }
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
            return "success";
        } catch (Exception e) {
            throw new Exception("Failed to update password: " + e.getMessage(), e);
        }
    }







    public List<UserResponse> searchUsers(String token, SearchRequest searchRequest) throws Exception {
        try {
            List<User> users = userRepository.searchUsers(searchRequest.getKeyword());
            List<User> filteredUsers = users.stream()
                    .filter(user -> user.getRoles().stream().noneMatch(role -> role.getName() == ERole.ROLE_ADMIN))
                    .collect(Collectors.toList());
            List<UserResponse> userResponses = filteredUsers.stream()
                    .map(user -> new UserResponse(
                            user.getMatPers(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getEmail(),
                            user.getTelephone()
                    ))
                    .collect(Collectors.toList());
            return userResponses;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void deleteUsers(DeleteUsersRequest deleteUsersRequest) throws Exception {
        log.info("#########################");
        log.info("this is the userRequestEmailsSize {}", deleteUsersRequest.getEmails().size());
        try {
            if (deleteUsersRequest.getEmails().size() == 1) {
                String email = deleteUsersRequest.getEmails().get(0);
                User user = userRepository.findAppUserByEmail(email);
                if (user != null) {
                    userRepository.delete(user);
                }
            } else {
                for (String email : deleteUsersRequest.getEmails()) {
                    User user = userRepository.findAppUserByEmail(email);
                    if (user != null) {
                        userRepository.delete(user);
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }



    public void banUserByEmail(String email) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new Exception("User not found for email: " + email);
        }

        User user = userOptional.get();
        user.setEnabled(false);
        userRepository.save(user);
    }


    /////
    public List<User> getUsersByRole(String roleName) {
        Role role = roleRepository.findByNames(roleName);
        if (role == null) {
            return Collections.emptyList();
        }
        return userRepository.findAllByRolesContains(role);
    }

    public long getTotalBannedUsers() {
        return userRepository.countByEnabledFalse();
    }





    @Transactional
    public User editUserProfile(String token, ProfileEditRequest request) throws Exception {
        try {
            // Extract user email from the token
            String email = jwtUtils.getUserNameFromJwtToken(token);
            Optional<User> userOptional = userRepository.findByEmail(email);

            if (userOptional.isEmpty()) {
                throw new Exception("User not found for token: " + token);
            }

            User user = userOptional.get();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setTelephone(request.getTelephone());
            // Assume birthDate is also a part of ProfileEditRequest
            if (request.getBirthDate() != null) {
                user.setBirthDate(request.getBirthDate());
            }

            User savedUser = userRepository.save(user);
            return savedUser;

        } catch (Exception e) {
            throw new Exception("Failed to update user: " + e.getMessage());
        }
    }




//    public User getCurrentUserEntityByToken(String token) throws Exception {
//        // Use the injected instance of JwtUtils to call getUserNameFromJwtToken
//        String username = jwtUtils.getUserNameFromJwtToken(token);
//
//        // Now find the user entity in the database using the extracted username
//        return userRepository.findByEmail(username)
//                .orElseThrow(() -> new Exception("User not found"));
//    }



    ////
    public User getUserByMatPers(Long matPers) {
        return userRepository.findByMatPers(matPers).orElse(null);
    }
}