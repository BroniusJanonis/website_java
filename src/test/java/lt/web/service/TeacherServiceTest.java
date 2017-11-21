package lt.web.service;

import com.sun.xml.internal.ws.api.model.ExceptionType;
import lt.web.modelDTO.*;
import lt.web.models.*;
import lt.web.repository.TeachersRep;
import lt.web.serviceDTO.TeachersServiceDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TeacherServiceTest {

    TeacherService teacherService;
    @Mock
    TeachersServiceDTO teachersServiceDTO;
    @Mock
    TeachersRep teachersRep;


    @Before
    public void setUp() throws Exception {
        // Sis pasako, kad Mockito duotu @Mock TeachersRep, kuris yra usmokintas
        MockitoAnnotations.initMocks(this);
        // kai iskvieciamas TeacherService objektas, tai bus jame jau TeachersRep repositorius, kuris yra uzmokintas ir kuri reikes apsirasyti @Test metoduose
        teacherService = new TeacherService(teachersRep);
    }

    @Test
    public void getAllTeachers() throws Exception {
        // pasikuriam Teachers modelio list'a
        Teachers teachers = new Teachers();
        teachers.setTeacherId(1);
        teachers.setName("name");
        teachers.setSurname("surname");
        teachers.setPhone("phone123");

        Subjects subjects = new Subjects();
        subjects.setSubjectId(1);
        subjects.setSubjectName("subjectsName");
        subjects.setTeacher(new Teachers());
        List<Subjects> subjectsList = new ArrayList<>();
        subjectsList.add(subjects);
        teachers.setSubject(subjectsList);

        SchoolClasses schoolClasses = new SchoolClasses();
        schoolClasses.setTitle("schoolClassTitle");
        schoolClasses.setTeacher(new Teachers());
        List<Children> childrenList = new ArrayList<>();
        childrenList.add(new Children());
        schoolClasses.setChildrenList(childrenList);
        teachers.setSchoolClasses(schoolClasses);

        Users users = new Users();
        users.setUserId(1);
        users.setEmail("usersEmail");
        users.setPassword("usersPassword");
        users.setPassword_auth("usersPasswordAuth");
        Roles roles = new Roles();
        roles.setRoleId(1);
        roles.setRoleTitle("roleTitle");
        List<Users> usersList = new ArrayList<>();
        usersList.add(users);
        roles.setUsersList(usersList);
        users.setRole(roles);
        teachers.setUser(users);

        List<Teachers> teachersList = new ArrayList<>();
        teachersList.add(teachers);

        // kai kreipiamasi i TeachersRep repositorijos metoda .findAll(), pagrazinti mokini lista > teachersList
        when(teachersRep.findAll()).thenReturn(teachersList);
        // iskvieciam List<Teachers) ir invokinant teachersRep.findAll() metoda gausime > teachersList
        List<Teachers> teachersListInvoked = teachersRep.findAll();
        // patikrinam ar gaunam tik viena lista, reikia vieno
        assertEquals(teachersListInvoked.size(), 1);
        // pasitikrinam, kad TeacherRep buvo iskviestas tik viena karta
        verify(teachersRep, times(1)).findAll();


        // pasikuriam TeachersDTO modelio list'a
        TeachersDTO teachersDTO = new TeachersDTO();
        teachersDTO.setTeacherId(1);
        teachersDTO.setName("name");
        teachersDTO.setSurname("surname");
        teachersDTO.setPhone("phone123");

        SubjectsDTO subjectsDTO = new SubjectsDTO();
        subjectsDTO.setSubjectId(1);
        subjectsDTO.setSubjectName("subjectsName");
        subjectsDTO.setTeacher(new Teachers());
        List<SubjectsDTO> subjectsListDTO = new ArrayList<>();
        subjectsListDTO.add(subjectsDTO);
        teachersDTO.setSubject(subjectsListDTO);

        SchoolClassesDTO schoolClassesDTO = new SchoolClassesDTO();
        schoolClassesDTO.setTitle("schoolClassTitle");
        schoolClassesDTO.setTeacher(new TeachersDTO());
        List<SchoolClassesDTO> schoolClassesDTOListDTO = new ArrayList<>();
        ChildrenDTO childrenDTO = new ChildrenDTO();
        List<ChildrenDTO> childrenDTOList = new ArrayList<>();
        childrenDTOList.add(childrenDTO);
        schoolClassesDTO.setChildrenList(childrenDTOList);
        teachersDTO.setSchoolClasses(schoolClassesDTO);

        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUserId(1);
        usersDTO.setEmail("usersEmail");
        usersDTO.setPassword("usersPassword");
        usersDTO.setPassword_auth("usersPasswordAuth");
        RolesDTO rolesDTO = new RolesDTO();
        rolesDTO.setRoleId(1);
        rolesDTO.setRoleTitle("roleTitle");
        List<UsersDTO> usersListDTO = new ArrayList<>();
        usersListDTO.add(usersDTO);
        rolesDTO.setUsersList(usersListDTO);
        usersDTO.setRoleDTO(rolesDTO);
        teachersDTO.setUser(usersDTO);

        List<TeachersDTO> teachersListDTO = new ArrayList<>();
        teachersListDTO.add(teachersDTO);

        // kai iskvieciamas teachersServiceDTO su jo metodu, tai pagrazinam List<TeachersDTO> list'a
        when(teachersServiceDTO.teacherDTOList(teachersList)).thenReturn(teachersListDTO);
        // per TeachersServiceDTO issikivieciam jo metoda, ir tikrinam ar metodas pagrazina tai, ko prazeme List<TeacherDTO>
        List<TeachersDTO> teachersListTest = teachersServiceDTO.teacherDTOList(teachersList);
        // issikvieciam lista ir patikrinam ar gavom viena
        assertEquals(teachersListTest.size(), 1);
        // patikrinam ar invokinant teachersServiceDTO panaudotas tik viena karta
        verify(teachersServiceDTO, times(1)).teacherDTOList(teachersList);
        // patikrinam ar invokintant TeachersServiceDTO, bent viena karta
        verify(teachersServiceDTO, Mockito.atLeast(1)).teacherDTOList(teachersList);
    }

    @Test
    public void saveAndFlushTeacher() throws Exception {
        Teachers teachers = new Teachers();
        teachers.setName("teachersName");
        // kai iskvieciamas teacherService.saveAndFlushTeacher() metodas, tai pagerazinam musu hardkorinta duomeni
        when(teacherService.saveAndFlushTeacher(teachers)).thenReturn(teachers);
        // issikvieciam Teacher
        Teachers mockTeacher = teacherService.saveAndFlushTeacher(teachers);
        // tikrinam ar mockTeacher pagrazins (issaugos) teacher
        assertEquals(mockTeacher, teachers);
        // patikrinam ar negaunam tuscio objekto
        assertTrue(mockTeacher != null);
    }

    @Test
    public void saveTeacher() throws Exception {
        Teachers teachers = new Teachers();
        teachers.setName("teachersName");
        // kai iskvieciamas teacherService.saveAndFlushTeacher() metodas, tai pagerazinam musu hardkorinta duomeni
        when(teacherService.saveTeacher(teachers)).thenReturn(teachers);
        // issikvieciam Teacher
        Teachers mockTeacher = teacherService.saveTeacher(teachers);
        // tikrinam ar mockTeacher pagrazins (issaugos) teacher
        assertEquals(mockTeacher, teachers);
        // patikrinam ar negaunam tuscio objekto
        assertTrue(mockTeacher != null);

        // kai kvieciame teachersRep.save() metoda, tikrinam
        when(teachersRep.save(teachers)).thenReturn(teachers);
        Teachers saveTeacher = teachersRep.save(teachers);
        assertEquals(saveTeacher.getName(), teachers.getName());

    }

    @Test
    public void deleteTeacher() throws Exception {
        // paserinam teacherId for repository
        int teacherId = 1;
        Teachers teachers = new Teachers();
        teachers.setTeacherId(teacherId);
        // void to Mockito negali buti naudojamas
        // nesu tikras, ka cia tikrint :]
        teachersRep.delete(teachers);
    }

    @Test
    public void findByTeacherId() throws Exception {
        // paserinam teacherId for repository
        int teacherId = 1;
        Teachers teachers = new Teachers();
        teachers.setName("teachersName");
        // tikrinam, kad metodas pagrazina Teacher
        when(teacherService.findByTeacherId(teacherId)).thenReturn(teachers);
        // issikvieciam metoda
        Teachers byTeacherIdServ = teacherService.findByTeacherId(teacherId);
        // tikrinam ar turi reiksme
        assertNotNull(byTeacherIdServ);

        // tikrinam, kad repositorius pagrazina Teacher
        when(teachersRep.findByTeacherId(teacherId)).thenReturn(teachers);
        Teachers byTeacherIdRep = teachersRep.findByTeacherId(teacherId);
        assertNotNull(byTeacherIdRep);
    }


}
