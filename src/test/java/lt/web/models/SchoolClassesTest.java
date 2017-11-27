package lt.web.models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SchoolClassesTest {

    private int schoolClassesId;
    private String title;
    private List<Children> childrenList;
    private Teachers teacher;
    private SchoolClasses schoolClasses;

    @Before
    public void setUp() throws Exception {
        this.schoolClassesId = 1;
        this.title = "title";
        this.childrenList = new ArrayList<>();
        this.teacher = new Teachers();
        this.schoolClasses = new SchoolClasses();
    }

    @Test
    public void getSchoolClassesId() throws Exception {
        schoolClasses.setSchoolClassesId(schoolClassesId);
        assertEquals(schoolClasses.getSchoolClassesId(), schoolClassesId);
    }

    @Test
    public void setSchoolClassesId() throws Exception {
        schoolClasses.setSchoolClassesId(schoolClassesId);
        assertEquals(schoolClasses.getSchoolClassesId(), schoolClassesId);
    }

    @Test
    public void getTitle() throws Exception {
        schoolClasses.setTitle(title);
        assertEquals(schoolClasses.getTitle(), title);
    }

    @Test
    public void setTitle() throws Exception {
        schoolClasses.setTitle(title);
        assertEquals(schoolClasses.getTitle(), title);
    }

    @Test
    public void getChildrenList() throws Exception {
        schoolClasses.setChildrenList(childrenList);
        assertTrue(schoolClasses.getChildrenList() instanceof ArrayList);
        assertEquals(schoolClasses.getChildrenList(), childrenList);
    }

    @Test
    public void setChildrenList() throws Exception {
        schoolClasses.setChildrenList(childrenList);
        assertTrue(schoolClasses.getChildrenList() instanceof ArrayList);
        assertEquals(schoolClasses.getChildrenList(), childrenList);
    }

    @Test
    public void getTeacher() throws Exception {
        schoolClasses.setTeacher(teacher);
        assertTrue(schoolClasses.getTeacher() instanceof Teachers);
        assertEquals(schoolClasses.getTeacher(), teacher);
    }

    @Test
    public void setTeacher() throws Exception {
        schoolClasses.setTeacher(teacher);
        assertTrue(schoolClasses.getTeacher() instanceof Teachers);
        assertEquals(schoolClasses.getTeacher(), teacher);
    }

}