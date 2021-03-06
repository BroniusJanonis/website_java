package lt.web.repository;

import lt.web.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRep extends JpaRepository<Roles, Long> {

    Roles findFirstByRoleId(int id);
}
