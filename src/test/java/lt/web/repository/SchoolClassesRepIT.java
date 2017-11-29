package lt.web.repository;

import lt.web.models.SchoolClasses;
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
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
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

        int schoolClassesId = 7;
        String schoolClassesTitle = "1C";
        int teacherId = 41;
        int i = schoolClassesRep.setTitleById(schoolClassesTitle, teacherId, schoolClassesId);

        assertTrue(i > 0);

    }

    @Test
    public void deleteSchoolClassesByTeacher_TeacherId() throws Exception {

        int teacherId = 10;
//        SchoolClasses firstByTeacher_teacherId = schoolClassesRep.findFirstByTeacher_TeacherId(teacherId);
        int teachId = schoolClassesRep.deleteSchoolClassesByTeacher_TeacherId(teacherId);

        assertTrue(teachId > 0);
    }

    @Test
    public void findFirstByTeacher_TeacherId() throws Exception {
        int teacherId = 10;
        int schoolClassesId = 9;

        SchoolClasses firstByTeacher_teacherId = schoolClassesRep.findFirstByTeacher_TeacherId(teacherId);

        assertEquals(schoolClassesId, firstByTeacher_teacherId.getSchoolClassesId());

    }

    @Test
    public void updateSchoolClassByTeacherId() throws Exception {
        int teacherId = 10;
        int schoolClassesId = 9;

        int schoolClasses = schoolClassesRep.updateSchoolClassByTeacherId(teacherId, schoolClassesId);

        assertTrue(schoolClasses > 0);
    }

    @Test
    public void findFirstBySchoolClassesId() throws Exception {

        int schoolClassesId = 9;
        int teacherId = 10;

        SchoolClasses firstBySchoolClassesId = schoolClassesRep.findFirstBySchoolClassesId(schoolClassesId);

        assertEquals(teacherId, firstBySchoolClassesId.getTeacher().getTeacherId());

    }

}