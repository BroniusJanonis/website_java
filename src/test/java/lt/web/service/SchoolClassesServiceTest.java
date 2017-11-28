package lt.web.service;

import lt.web.models.SchoolClasses;
import lt.web.models.Teachers;
import lt.web.repository.SchoolClassesRep;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SchoolClassesServiceTest {

    SchoolClassesService schoolClassesService;
    @Mock
    private SchoolClassesRep schoolClassesRep;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        schoolClassesService = new SchoolClassesService(schoolClassesRep);
    }

    @Test
    public void setTitleById() throws Exception {
        int schoolClassesId = 1;
        String schoolClassTitle = "schoolClassTitle";
        int teacherId = 1;
        int i = 1;

        when(schoolClassesService.setTitleById(schoolClassTitle, teacherId, schoolClassesId)).thenReturn(i);
        assertEquals(schoolClassesService.setTitleById(schoolClassTitle, teacherId, schoolClassesId), i);

        verify(schoolClassesRep, times(1)).setTitleById(schoolClassTitle, teacherId, schoolClassesId);
        }

    @Test
    public void save() throws Exception {
        int schoolClassesId = 1;
        SchoolClasses schoolClasses = new SchoolClasses();
        schoolClasses.setSchoolClassesId(schoolClassesId);
        schoolClasses.setTeacher(new Teachers());

        when(schoolClassesRep.saveAndFlush(schoolClasses)).thenReturn(schoolClasses);
        assertEquals(schoolClassesRep.saveAndFlush(schoolClasses), schoolClasses);

        verify(schoolClassesRep, times(1)).saveAndFlush(schoolClasses);
    }

    @Test
    public void deleteSchoolclassesByTeacherId() throws Exception {
        int teacherId = 1;
        schoolClassesRep.deleteSchoolClassesByTeacher_TeacherId(teacherId);
        verify(schoolClassesRep, times(1)).deleteSchoolClassesByTeacher_TeacherId(teacherId);
    }

    @Test
    public void getSchoolChlassesList() throws Exception {

        List<SchoolClasses> schoolClassesList = new ArrayList<>();
        schoolClassesList.add(new SchoolClasses());
        schoolClassesList.add(new SchoolClasses());

        when(schoolClassesService.getSchoolChlassesList()).thenReturn(schoolClassesList);

        assertEquals(schoolClassesService.getSchoolChlassesList().size(), 2);

//        when(schoolClassesRep.findAll()).thenReturn(schoolClassesList);
//        assertEquals(schoolClassesRep.findAll().size(), 2);

        verify(schoolClassesRep, times(1)).findAll();

    }

    @Test
    public void updateSchoolClass() throws Exception {
        SchoolClasses schoolClasses = new SchoolClasses();
        schoolClasses.setSchoolClassesId(1);
        when(schoolClassesRep.saveAndFlush(schoolClasses)).thenReturn(schoolClasses);
        when(schoolClassesService.updateSchoolClass(schoolClasses)).thenReturn(schoolClasses);

        assertTrue(schoolClassesRep.saveAndFlush(schoolClasses) instanceof SchoolClasses);

        verify(schoolClassesRep, times(1)).saveAndFlush(schoolClasses);
    }

    @Test
    public void findSchoolClassByTeacherId() throws Exception {
        int teacherId = 1;
        SchoolClasses schoolClasses = new SchoolClasses();
        schoolClasses.setTeacher(new Teachers(teacherId));

        when(schoolClassesRep.findFirstByTeacher_TeacherId(teacherId)).thenReturn(schoolClasses);
        when(schoolClassesService.findSchoolClassByTeacherId(teacherId)).thenReturn(schoolClasses);
//        assertTrue(schoolClassesRep.findFirstByTeacher_TeacherId(teacherId) instanceof SchoolClasses);
        assertEquals(schoolClassesService.findSchoolClassByTeacherId(teacherId), schoolClasses);
        verify(schoolClassesRep, times(1)).findFirstByTeacher_TeacherId(teacherId);

    }

    @Test
    public void saveClassesTitle() throws Exception {

        String title = "title";
        SchoolClasses schoolClasses = new SchoolClasses();
        schoolClasses.setTitle(title);

        when(schoolClassesRep.saveAndFlush(any(SchoolClasses.class))).thenReturn(schoolClasses);
//        doReturn(schoolClasses).when(schoolClassesRep).saveAndFlush(new SchoolClasses(title));
//        when(schoolClassesService.saveClassesTitle(title)).thenReturn(schoolClasses);
        assertEquals(schoolClasses, schoolClassesService.saveClassesTitle(title));

    }

    @Test
    public void deleteSchoolclassesByClassId() throws Exception {
        SchoolClasses schoolClasses = new SchoolClasses();
        int schoolClassId = 1 ;
        schoolClasses.setSchoolClassesId(schoolClassId);

        schoolClassesRep.delete(schoolClasses);
        verify(schoolClassesRep, times(1)).delete(schoolClasses);
    }

    @Test
    public void updateSchoolClassByTeacherIdAndSchoolClassId() throws Exception {
        int teacherId = 1;
        int schoolClassId = 1;
        schoolClassesRep.updateSchoolClassByTeacherId(teacherId, schoolClassId);
        verify(schoolClassesRep, times(1)).updateSchoolClassByTeacherId(teacherId, schoolClassId);
    }

    @Test
    public void findSchoolClassByschoolClassesId() throws Exception {

        int schoolClassesId = 1;
        SchoolClasses schoolClasses = new SchoolClasses();
        schoolClasses.setSchoolClassesId(schoolClassesId);
        when(schoolClassesRep.findFirstBySchoolClassesId(schoolClassesId)).thenReturn(schoolClasses);

        assertEquals(schoolClasses, schoolClassesService.findSchoolClassByschoolClassesId(schoolClassesId));

    }

}