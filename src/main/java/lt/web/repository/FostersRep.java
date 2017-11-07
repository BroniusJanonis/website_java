package lt.web.repository;

import lt.web.models.Fosters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FostersRep extends JpaRepository<Fosters, Long>{

    @Modifying
    @Query("update Fosters set name = :name, surname = :surname, phone = :phone, address = :address, user.userId = :userId  where fosterId = :fosterId")
    @Transactional
    void updateFoster(@Param("name") String name, @Param("surname") String surname , @Param("phone") String phone, @Param("address") String address,  @Param("fosterId") int fosterId, @Param("userId") int userId);

}
