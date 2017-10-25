package lt.web.repository;

import lt.web.models.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenRep extends JpaRepository<Children, Long>{
}
