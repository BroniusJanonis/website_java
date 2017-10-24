package lt.web.repository;

import lt.web.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<Users, Long> {
    // pagal savo modeli per JpaRepository galim kurtis visa CRUD'a
    Users findByEmail(String email);

}
