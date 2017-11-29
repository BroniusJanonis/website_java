package lt.web.repository;

import lt.web.models.Children;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChildrenRepIT {

    @Autowired
    ChildrenRep childrenRep;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void updateChild() throws Exception {
        String name = "childName1Test";
        String surname = "childSurname1Test";
        int fosterId = 1;
        int schoolClassesId = 7;
        int userId = 34;
        int childId = 3;

        int children = childrenRep.updateChild(name, surname, fosterId, schoolClassesId, userId, childId);

        assertTrue(children > 0);

    }

    @Test
    public void saveChild() throws Exception {
        String name = "childName1Test";
        String surname = "childSurname1Test";
        int fosterId = 1;
        int schoolClassesId = 7;
        int userId = 34;

        int children = childrenRep.saveChild(name, surname, fosterId, schoolClassesId, userId);

        assertTrue(children > 0);
    }

    @Test
    public void updateChildrensFoster() throws Exception {
        int childId = 3;
        int fosterId = 1;

        int children = childrenRep.updateChildrensFoster(childId, fosterId);

        assertTrue(children > 0);

    }

    @Test
    public void updateChildrensClasses() throws Exception {
        int schoolClassesId = 7;
        int childId = 3;

        int children = childrenRep.updateChildrensClasses(schoolClassesId, childId);

        assertTrue(children > 0);
    }

}