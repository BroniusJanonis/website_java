package lt.web.repository;

import lt.web.models.SchoolClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassesRep extends JpaRepository<SchoolClasses, Long>{
}
