package lt.web.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureTestDatabase
@DataJpaTest
public class SchoolClassesRepIT {

    @Autowired
    SchoolClassesRep schoolClassesRep;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void setTitleById() throws Exception {

//        @Query("update SchoolClasses c set c.title = :schoolClassesTitle, c.teacher.teacherId = :teacherId where c.schoolClassesId = :schoolClassesId")
//        int setTitleById(@Param("schoolClassesTitle") String schoolClassesTitle, @Param("teacherId") int teacherId , @Param("schoolClassesId") int schoolClassesId);

        String schoolClassesTitle = "1C-Test";
        int teacherId = 41;
        int schoolClassesId = 7;
        int i = schoolClassesRep.setTitleById(schoolClassesTitle, teacherId, schoolClassesId);

        assertTrue(i > 0);

    }

    @Test
    public void deleteSchoolClassesByTeacher_TeacherId() throws Exception {
    }

    @Test
    public void findFirstByTeacher_TeacherId() throws Exception {
    }

    @Test
    public void updateSchoolClassByTeacherId() throws Exception {
    }

    @Test
    public void findFirstBySchoolClassesId() throws Exception {
    }

}