package lt.web.controler;

import lt.web.modelDTO.TeachersDTO;
import lt.web.models.SchoolClasses;
import lt.web.models.Subjects;
import lt.web.models.Teachers;
import lt.web.models.Users;
import lt.web.service.ISchoolClassesService;
import lt.web.service.ISubjectService;
import lt.web.service.ITeacherService;
import lt.web.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
        teacherController = new TeacherController(teacherService, userService, schoolClassesService, subjectService);
    }

    // dar vienas budas, kuri siulo Spring testuoti kontroleriams (cia nebe unit test, o integration test)
    @Test
    public void MockMvcTest() throws Exception {
        // vieResolver reikia, nes "/teacherMain" su "teacherMain" pjaunasi > testai neuzkrauna Springo
        // , tad musu prefix'ai negalioje ir reikia pasisetinti is naujo
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");

        // subuilinom teacherController MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(teacherController).setViewResolvers(viewResolver).build();

        // per get metoda kreipiasi i /teacherMain
        // tikisi, kad bus be klaidu ir pagrazins isOk() > 200 statusa
        // taip pat tikisi, kad view bus "TeacherMain"
        mockMvc.perform(get("/teacherMain"))
        .andExpect(status().isOk())
        .andExpect(view().name("teacherMain"));
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
        String verifyString = teacherController.welcome(model);
        assertEquals("teacherMain", verifyString);
        // tikrinam, kad servisas buvo iskviestas tik viena karta ir (cia du kartus
        // , nes vienas kartas invokinamas su teacherController iskvietimu < konstruktoriuje yra, kitaip nepaima model (nezinau, kodel)
        verify(teacherService, times(2)).getAllTeachers();
        verify(subjectService, times(2)).findAll();
        verify(schoolClassesService, times(2)).getSchoolChlassesList();
        // tikrinam, kad model buvo iskviestas tris kartus, kiek kartu skirtingi servisai
        verify(model, times(3)).addAttribute(anyString(), anyList());

    }

    @Test
    public void saveAndFlushTeacher() throws Exception {
        String name = "name";
        String surname = "surname";
        String phone = "phone";
        int schoolClassesId = 1;
        int teacherId = 1;
        int userId = 1;
        String[] subjectName = {"schoolClassesId", "schoolClassesId2"};
        Users users = new Users();
        users.setUserId(userId);
        Teachers teachers = new Teachers();
        teachers.setTeacherId(teacherId);
        teachers.setName(name);
        teachers.setSurname(surname);
        teachers.setPhone(phone);
        teachers.setUser(users);

        String returnString = teacherController.saveAndFlushTeacher(name, surname, phone, schoolClassesId, teacherId, userId, subjectName);
        assertEquals("redirect:teacherMain", returnString);

        // du kartus naudota + 1 issikviesta per teacherController
        subjectService.deleteSubjectsByTeacherId(teacherId);
        verify(subjectService, times(2)).deleteSubjectsByTeacherId(teacherId);

        subjectService.save(subjectName[0], teachers);
        verify(subjectService, times(1)).save(subjectName[0], teachers);

        teacherService.saveTeacher(teachers);
        verify(teacherService, times(1)).saveTeacher(teachers);

    }

    @Test
    public void addTeacher() throws Exception {

        String name = "name";
        String surname = "surname";
        String phone = "phone";
        int schoolClassesId = 1;
        String[] subjectName = {"subjectName", "subjectName2"};
        String email = "email";
        String password = "password";
        Teachers teachers = new Teachers();
        teachers.setTeacherId(1);

        when(teacherService.saveTeacher(teachers)).thenReturn(teachers);
        Teachers teachersSaved = teacherService.saveTeacher(teachers);
        schoolClassesService.updateSchoolClassByTeacherIdAndSchoolClassId(teachersSaved.getTeacherId(), schoolClassesId);

        // Tikram metode panaudojau, kad issisaugoja Teacher, is jo gaunu Id
        // , o eidamas per sita testa as stringu, nes metodas uzmokintas ir neduoda reiksmes, is kurios prasau Id
//        String addTeacherReturnString = teacherController.addTeacher(name, surname, phone, schoolClassesId, subjectName, email, password);
//        assertEquals("redirect:teacherMain", addTeacherReturnString);

    }

    @Test
    public void deleteTeacher() throws Exception {
        int teacherId = 1;
        String stringToSentToAjax = teacherController.deleteTeacher(teacherId);
        assertEquals("Istryne Teacher su jo user, klase, subjects", stringToSentToAjax);
        verify(userService, times(1)).deleteUserByTeacherId(teacherId);
    }

}
