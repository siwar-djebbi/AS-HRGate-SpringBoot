package as.tn.hrgate.Repository;

import as.tn.hrgate.Entities.ERole;
import as.tn.hrgate.Entities.Role;
import as.tn.hrgate.Entities.User;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    Boolean existsByEmail(String email);

     User findByFirstName(String firstname);
//User findByMatPers(Long matricule);
Optional<User> findByMatPers(Long matPers);

    Optional<User> findByEmail(String email);
//    @Query(value="SELECT  email FROM user where email=:email",nativeQuery = true)
//    User findByMail(@Param("email") String email);


    @Query("select u from User u where (u.firstName like concat(:keyword, '%')" +
            " or u.lastName like concat(:keyword, '%') or u.email like concat(:keyword, '%'))" +
            " and u.deleted = false")


    List<User> searchUsers(String keyword);

    User findAppUserByEmail(String email);


    long countByEnabledFalse();

    @Query("SELECT u FROM User u JOIN u.roless r WHERE r.name = :roleName")
    List<User> findByRolesName(@Param("roleName") ERole roleName);


/////
@Query("select u from User u join u.roless r where r.idRole = :roleId")
List<User> findAllByRolesContains(@Param("roleId") Role role);

    ///List<User> findByRole(String roleChef);


}
