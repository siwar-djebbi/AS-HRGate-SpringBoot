package as.tn.hrgate.Repository;

import as.tn.hrgate.Entities.ERole;
import as.tn.hrgate.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(ERole name);

    @Query(value="SELECT  id_role,name FROM roles where name=:name",nativeQuery = true)
    Role findByNames(@Param("name") String name);


}
