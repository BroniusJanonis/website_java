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

}
