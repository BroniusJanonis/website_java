package lt.web.repository;

import lt.web.models.SchoolClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SchoolClassesRep extends JpaRepository<SchoolClasses, Long>{
    @Modifying
    @Query("update SchoolClasses c set c.title = :schoolClassesTitle, c.teacher.teacherId = :teacherId where c.schoolClassesId = :schoolClassesId")
    @Transactional
//    SQL pasitiktint pirma reikia > update webschoolclasses set title = 'title', teacher_id = 5 where school_classes_id = 3
    int setTitleById(@Param("schoolClassesTitle") String schoolClassesTitle, @Param("teacherId") int teacherId , @Param("schoolClassesId") int schoolClassesId);

//    @Modifying
//    @Query("delete from  SchoolClasses c where c.teacher.teacherId= :teacherId")
//    @Transactional
//    void deleteSchoolClassesByTeacher_TeacherId(@Param("teacherId") int teacherId);

    int deleteSchoolClassesByTeacher_TeacherId(int teacherId);
    SchoolClasses findFirstByTeacher_TeacherId(int teacherId);

//    @Modifying
//    @Query(nativeQuery = true, name = "UPDATE webschoolclasses SET teacher_id= :teacher_id WHERE school_classes_id= :schoolClassesId")
//    @Transactional
//    void setTeacherIdToNullWhereSchoolClassesIdIs(@Param("schoolClassesId") int schoolClassesId);

    @Modifying
    @Query("update SchoolClasses c set c.teacher.teacherId = :teacherId where c.schoolClassesId = :schoolClassesId")
    @Transactional
    int updateSchoolClassByTeacherId(@Param("teacherId") int teacherId, @Param("schoolClassesId") int schoolClassesId);

    SchoolClasses findFirstBySchoolClassesId(@Param("schoolClassesId") int schoolClassesId);
}
