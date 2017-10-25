package lt.web.repository;

import lt.web.models.Fosters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FostersRep extends JpaRepository<Fosters, Long>{
}
