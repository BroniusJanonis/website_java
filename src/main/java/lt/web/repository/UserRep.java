package lt.web.repository;

import lt.web.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends JpaRepository<Users, Long> {
    // pagal savo modeli per JpaRepository galim kurtis visa CRUD'a
    Users findFirstByEmail(String email);

}
