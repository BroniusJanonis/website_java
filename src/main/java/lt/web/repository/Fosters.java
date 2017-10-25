package lt.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Fosters extends JpaRepository<Fosters, Long>{
}
