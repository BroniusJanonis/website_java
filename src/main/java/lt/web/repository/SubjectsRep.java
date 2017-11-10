package lt.web.repository;

import lt.web.models.Subjects;
import lt.web.models.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SubjectsRep extends JpaRepository<Subjects, Long> {
    @Modifying
    @Query("update Subjects c set c.subjectName = :subjectName, c.teacher.teacherId = :teacherId where c.subjectId = :subjectId")
    @Transactional
//    update websubjects set subject_name = 'subjectName1', teacher_id = 5 where subject_id = 2
    int setSubjectById(@Param("subjectName") String subjectName, @Param("teacherId") int teacherId , @Param("subjectId") int subjectId);

    @Modifying
    @Query(nativeQuery = true, value = "SELECT teacher_id FROM webteachers WHERE teacher_id IN (SELECT teacher_id FROM websubjects WHERE subject_name= :subjectName)")
    @Transactional
    List<Integer> getTeachersListBySubjectName(@Param("subjectName") String subjectName);
    Subjects findBySubjectId(@Param("subjectId") int subjectId);
    Subjects findSubjectsByTeacher_TeacherIdAndSubjectName(@Param("teacherId") int teacherId, @Param("subjectName") String subjectName);
    List<Subjects> findBySubjectName(String subjectName);
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM websubjects WHERE subject_name= :subjectName")
    @Transactional
    void deleteSubjectsBySubjectName(@Param("subjectName") String subjectName);
    List<Subjects> findSubjectsByTeacher_TeacherId(@Param("teacherId") int teacherId);
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM websubjects where teacher_id= :teacherId")
    void deleteSubjectsByTeacher_TeacherId(@Param("teacherId") int teacherId);
}
