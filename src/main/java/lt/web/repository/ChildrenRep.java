package lt.web.repository;

import lt.web.models.Children;
import lt.web.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChildrenRep extends JpaRepository<Children, Long>{

    @Modifying
    @Query("update Children set name = :name, surname = :surname, foster.fosterId = :fosterId, schoolClasses.schoolClassesId = :schoolClassesId, user.userId = :userId  where childId = :childId")
//    Postgresql select: > update webchildren set name = 'name', surname = 'surname', foster_id = 1, school_classes_id = 3, user_id = 2  where child_id = 2
    @Transactional
//    update websubjects set subject_name = 'subjectName1', teacher_id = 5 where subject_id = 2
    void updateChild(@Param("name") String name, @Param("surname") String surname , @Param("fosterId") int fosterId, @Param("schoolClassesId") int schoolClassesId, @Param("userId") int userId, @Param("childId") int childId);
    @Modifying
//    insert into webchildren (name, surname, foster_id, school_classes_id, user_id) values ('name1', 'surname1', 1, 3, 34)
    @Query(nativeQuery = true, value = "INSERT INTO webchildren (name, surname, foster_id, school_classes_id, user_id) VALUES (:name, :surname, :fosterId, :schoolClassesId, :userId)")
    @Transactional
    void saveChild(@Param("name") String name, @Param("surname") String surname , @Param("fosterId") int fosterId, @Param("schoolClassesId") int schoolClassesId, @Param("userId") int userId);

    @Modifying
    @Query("update Children set foster.fosterId = :fosterId where childId = :childId")
//    update webchildren set foster_id= 1 where child_id = 2
    @Transactional
    void updateChildrensFoster(@Param("childId") int childId, @Param("fosterId") int fosterId);
}
