package lt.web.repository;

import lt.web.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRep extends JpaRepository<Users, Long> {
    // pagal savo modeli per JpaRepository galim kurtis visa CRUD'a
    Users findFirstByEmail(String email);
    Users findFirstByUserId(int id);

    @Modifying
    // naudot reikia ne DB, bet Modelio selektus
    @Query("DELETE FROM Users WHERE userId IN (SELECT user.userId FROM Teachers WHERE teacherId= :teacherId)")
    @Transactional
    void deleteUsersByTeacherId(@Param("teacherId") int teacherId);

    @Modifying
//    DELETE FROM webusers WHERE user_id IN (SELECT webchildren.user_id FROM webchildren WHERE user_id= 46)
    @Query("DELETE FROM Users WHERE userId IN (SELECT user.userId FROM Children WHERE childId= :childId)")
    @Transactional
    void deleteUsersByChildId(@Param("childId") int childId);

    @Modifying
//    DELETE FROM webusers where user_id IN (SELECT webfosters.user_id FROM webfosters WHERE webfosters.foster_id = 6)
    @Query("DELETE FROM Users WHERE userId IN (SELECT user.userId FROM Fosters WHERE fosterId = :fosterId)")
    @Transactional
    void deleteUsersByFosterId(@Param("fosterId") int fosterId);

}
