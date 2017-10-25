package lt.web.repository;

import lt.web.models.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRep extends JpaRepository<Teachers, Long>{
}
