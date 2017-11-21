package lt.web.repository;

import lt.web.models.Teachers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;


import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeachersRepIT {

    @Autowired
    TeachersRep teachersRep;

    // cia reiketu pasisetinti testine duomenu baze, kurioje pasetinsim testinius duomenis
    // o @After naudosime, kad istrintume testine duomenu baze
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
        List<Teachers> teachersRepAll = teachersRep.findAll();
        assertEquals(teachersRepAll.get(0).getName(), "test");
    }

    @Test
    public void findByTeacherId() throws Exception {
        int teacherId = 1;
        Teachers byTeacherId = teachersRep.findByTeacherId(teacherId);
        assertEquals(byTeacherId.getName(), "test");
        // nereikia sio, nes jis visada bus lygus
//        assertTrue(byTeacherId instanceof Teachers);
        assertTrue(byTeacherId != null);
    }

}