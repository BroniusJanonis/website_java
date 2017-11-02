package lt.web.repository;

import lt.web.models.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface SubjectsRep extends JpaRepository<Subjects, Long> {
    @Modifying
    @Query("update Subjects c set c.subjectName = :subjectName, c.teacher.teacherId = :teacherId where c.subjectId = :subjectId")
    @Transactional
//    update websubjects set subject_name = 'subjectName1', teacher_id = 5 where subject_id = 2
    int setSubjectById(@Param("subjectName") String subjectName, @Param("teacherId") int teacherId , @Param("subjectId") int subjectId);
}
