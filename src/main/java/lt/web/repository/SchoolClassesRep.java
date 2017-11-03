package lt.web.repository;

import lt.web.models.SchoolClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    void deleteSchoolClassesByTeacher_TeacherId(int teacherId);
}
