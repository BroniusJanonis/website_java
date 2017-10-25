package lt.web.repository;

import lt.web.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRep extends JpaRepository<Roles, Long> {
}
