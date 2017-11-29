package lt.web.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChildrenTest {

    private int childId;
    private String name;
    private String surname;
    private Fosters foster;
    private SchoolClasses schoolClasse;
    private Users user;
    Children children;

    @Before
    public void setUp() throws Exception {
        children = new Children();
    }

    @Test
    public void getChildId() throws Exception {
        childId = 1;
        children.setChildId(childId);
        assertEquals(childId, children.getChildId());
    }

    @Test
    public void setChildId() throws Exception {
        childId = 1;
        children.setChildId(childId);
        assertEquals(childId, children.getChildId());
    }

    @Test
    public void getName() throws Exception {
        name = "name";
        children.setName(name);
        assertEquals(name, children.getName());
    }

    @Test
    public void setName() throws Exception {
        name = "name";
        children.setName(name);
        assertEquals(name, children.getName());
    }

    @Test
    public void getSurname() throws Exception {
        surname = "surname";
        children.setSurname(surname);
        assertEquals(surname, children.getSurname());
    }

    @Test
    public void setSurname() throws Exception {
        surname = "surname";
        children.setSurname(surname);
        assertEquals(surname, children.getSurname());
    }

    @Test
    public void getFoster() throws Exception {
        foster = new Fosters();
        children.setFoster(foster);
        assertTrue(children.getFoster() instanceof Fosters);
        assertEquals(foster, children.getFoster());
    }

    @Test
    public void setFoster() throws Exception {
        foster = new Fosters();
        children.setFoster(foster);
        assertTrue(children.getFoster() instanceof Fosters);
        assertEquals(foster, children.getFoster());
    }

    @Test
    public void getSchoolClasses() throws Exception {
        schoolClasse = new SchoolClasses();
        children.setSchoolClasses(schoolClasse);
        assertEquals(schoolClasse, children.getSchoolClasses());
    }

    @Test
    public void setSchoolClasses() throws Exception {
        schoolClasse = new SchoolClasses();
        children.setSchoolClasses(schoolClasse);
        assertEquals(schoolClasse, children.getSchoolClasses());
    }

    @Test
    public void getUser() throws Exception {
        user = new Users();
        children.setUser(user);
        assertEquals(user, children.getUser());
    }

    @Test
    public void setUser() throws Exception {
        user = new Users();
        children.setUser(user);
        assertEquals(user, children.getUser());
    }

}