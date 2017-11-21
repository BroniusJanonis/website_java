package lt.web.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class TeachersTest {

    // susikuriam objekta, nes ji naudosime
    Teachers teachers;
    Subjects subjects;
    SchoolClasses schoolClasses;
    Users users;
    // nieko nemokinam, nes nera zemesniu ar aukstesniu sluoksniu Modelyje, taciau servisuose ar kontroliuose bus, tad ten teks mockint

    // bus naudojamas kiekviena karta, kai pajungsime metoda su @Test (@After butu naudojam, kai pasibaigia @Test metodas)
    // dar yra @BeforeClass ir @AfterClass, tai galios pries ir po klases (Pvz.: TeachersTest), bet ne po kiekvieno metodo
    @Before
    public void setUp() throws Exception {
        // bus issikviestas objetas kiekvieno testo inicijavimo metu
        teachers = new Teachers();
        subjects = new Subjects();
        schoolClasses = new SchoolClasses();
        users = new Users();
    }

    @Test
    public void getTeacherId() throws Exception {
        // patikrinam ar pasetinus tikrai gaunamas musu duomuo (testuojam getTeacherId() metoda)
        int id = 1;
        teachers.setTeacherId(id);
        assertEquals(id, teachers.getTeacherId());
    }

    @Test
    public void setTeacherId() throws Exception {
        // gal kaip kitaip testuot seteri, bet realiai gal uztenka modelyje vieno set/get unit testo?
        int id = 1;
        teachers.setTeacherId(id);
        assertEquals(id, teachers.getTeacherId());
        assertNotNull(teachers);
    }

    @Test
    public void getName() throws Exception {
        String name = "name";
        teachers.setName(name);
        assertEquals(name, teachers.getName());
    }

    @Test
    public void setName() throws Exception {
        String name = "name";
        teachers.setName(name);
        assertEquals(name, teachers.getName());
        assertNotNull(teachers);
    }

    @Test
    public void getSurname() throws Exception {
        String surname = "surname";
        teachers.setSurname(surname);
        assertEquals(surname, teachers.getSurname());
    }

    @Test
    public void setSurname() throws Exception {
        String surname = "surname";
        teachers.setSurname(surname);
        assertEquals(surname, teachers.getSurname());
        assertNotNull(teachers);
    }

    @Test
    public void getPhone() throws Exception {
        String phone = "phone123";
        teachers.setPhone(phone);
        assertEquals(phone, teachers.getPhone());
    }

    @Test
    public void setPhone() throws Exception {
        String phone = "phone123";
        teachers.setPhone(phone);
        assertEquals(phone, teachers.getPhone());
        assertNotNull(teachers);
    }

    @Test
    public void getSubject() throws Exception {
        List<Subjects> subjectsList = new ArrayList<>();
        subjectsList.add(new Subjects());
        subjectsList.add(new Subjects());

        teachers.setSubject(subjectsList);
        assertEquals(subjectsList, teachers.getSubject());
        assertNotNull(teachers.getSubject());
        assertTrue(teachers.getSubject() != null);
    }

    @Test
    public void setSubject() throws Exception {
        List<Subjects> subjectsList = new ArrayList<>();
        subjectsList.add(new Subjects());
        subjectsList.add(new Subjects());

        teachers.setSubject(subjectsList);
        assertEquals(subjectsList, teachers.getSubject());
        assertTrue(teachers.getSubject() != null);
    }

    @Test
    public void getSchoolClasses() throws Exception {
        String schoolClassTitle = "title";
        schoolClasses.setTitle(schoolClassTitle);
        teachers.setSchoolClasses(schoolClasses);

        assertEquals(schoolClassTitle, teachers.getSchoolClasses().getTitle());
        assertNotNull(teachers.getSchoolClasses());
        assertTrue(teachers.getSchoolClasses().getTitle() != null);
    }

    @Test
    public void setSchoolClasses() throws Exception {
        String schoolClassTitle = "title";
        schoolClasses.setTitle(schoolClassTitle);
        teachers.setSchoolClasses(schoolClasses);

        assertEquals(schoolClassTitle, teachers.getSchoolClasses().getTitle());
        assertNotNull(teachers.getSchoolClasses());
//        assertTrue(teachers.getSchoolClasses().getTitle() != null);
    }

    @Test
    public void getUser() throws Exception {
        String email = "email";
        users.setEmail(email);
        teachers.setUser(users);

        assertEquals(email, teachers.getUser().getEmail());
        assertTrue(teachers.getUser() != null);
    }

    @Test
    public void setUser() throws Exception {
        String email = "email";
        users.setEmail(email);
        teachers.setUser(users);

        assertEquals(email, teachers.getUser().getEmail());
        assertNotNull(teachers.getUser());
    }


}
