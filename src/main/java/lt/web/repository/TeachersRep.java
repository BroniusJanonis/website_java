package lt.web.repository;

import lt.web.models.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachersRep extends JpaRepository<Teachers, Long>{

    List<Teachers> findAll();
    Teachers findByTeacherId(@Param("teacherId") int teacherId);

}
