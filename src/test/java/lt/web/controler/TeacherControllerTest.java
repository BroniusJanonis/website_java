package lt.web.controler;

import lt.web.modelDTO.TeachersDTO;
import lt.web.models.SchoolClasses;
import lt.web.models.Subjects;
import lt.web.models.Teachers;
import lt.web.service.ISchoolClassesService;
import lt.web.service.ISubjectService;
import lt.web.service.ITeacherService;
import lt.web.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TeacherControllerTest {

    TeacherController teacherController;
    @Mock
    ITeacherService teacherService;
    @Mock
    IUserService userService;
    @Mock
    ISchoolClassesService schoolClassesService;
    @Mock
    ISubjectService subjectService;
    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        teacherController = new TeacherController();
    }

    @Test
    public void welcome() throws Exception {

        List<TeachersDTO> teachersListDTO = new ArrayList<>();
        TeachersDTO teachersDTO = new TeachersDTO();
        teachersDTO.setName("teachersName");
        teachersListDTO.add(teachersDTO);

        List<Subjects> subjectsList = new ArrayList<>();
        Subjects subjects = new Subjects();
        subjects.setSubjectName("SubjectName");
        subjectsList.add(subjects);

        List<SchoolClasses> schoolClassesList = new ArrayList<>();
        SchoolClasses schoolClasses = new SchoolClasses();
        schoolClasses.setTitle("SchoolclassTitle");
        schoolClassesList.add(schoolClasses);

        // tikrinam mokintus servisus
        when(teacherService.getAllTeachers()).thenReturn(teachersListDTO);
        List<TeachersDTO> allTeachersDTO = teacherService.getAllTeachers();
        assertEquals(allTeachersDTO, teachersListDTO);
        assertNotNull(allTeachersDTO);
        assertEquals(allTeachersDTO.size(), 1);

        when(subjectService.findAll()).thenReturn(subjectsList);
        List<Subjects> subjList = subjectService.findAll();
        // JUnit assert
        assertNotNull(subjList);
        assertEquals(subjList.size(), 1);

        when(schoolClassesService.getSchoolChlassesList()).thenReturn(schoolClassesList);
        List<SchoolClasses> schoolChlassesList = schoolClassesService.getSchoolChlassesList();
        assertEquals(schoolChlassesList.size(), 1);

//        // tikrinam metoda ar grazina stringa, issikvieciam kontrolerio metoda su mockintu modeliu
//        String verifyString = teacherController.welcome(model);
//        assertEquals("teacherMain", verifyString);
        // tikrinam, kad servisas buvo iskviestas tik viena karta ir
        verify(teacherService, times(1)).getAllTeachers();
        verify(subjectService, times(1)).findAll();
        verify(schoolClassesService, times(1)).getSchoolChlassesList();
        // tikrinam, kad model buvo iskviestas tris kartus, kiek kartu skirtingi servisai
//        verify(model, times(3)).addAttribute(anyString(),anyList());

    }

    @Test
    public void saveAndFlushTeacher() throws Exception {
    }

    @Test
    public void addTeacher() throws Exception {
    }

    @Test
    public void deleteTeacher() throws Exception {
    }

}
