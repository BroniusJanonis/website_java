package lt.web.controler;

import lt.web.modelDTO.TeachersDTO;
import lt.web.models.*;
import lt.web.service.IChildrenService;
import lt.web.service.ISchoolClassesService;
import lt.web.service.ITeacherService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolClassesControllerTest {


    @Mock
    private ISchoolClassesService schoolClassesService;
    @Mock
    private IChildrenService childrenService;
    @Mock
    private ITeacherService teacherService;
    // issikvieciam kontroleri, kuriame injectinsim visus mockus
    @InjectMocks
    private SchoolClassesController schoolClassesController;
    // MockMvc mokas, kuris sugeneruos mockinta kontroleri pagal schoolClassesCtonrolelr ir subuildins
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        // Mockito uzmokina servisus
        MockitoAnnotations.initMocks(this);

        // kadangi prefix ir sufix nenaudojamas paleidimo metu, tad turime pasiteikti, jog nesipjautu su mano apsirasytais metodu mapais bei returnais
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");

        // pagal schoolClassesController(kuriame jau injectinti @Mock servisai), pabuildinam, jog nenaudotu tomcat, webservisu
        mockMvc = MockMvcBuilders.standaloneSetup(schoolClassesController).setViewResolvers(viewResolver).build();
    }

    //        Reikia pabaigti param ir issikviest per modelius bei sutikrint
    @Test
    public void welcome() throws Exception {
        // mockinis duomuo, kuris bus naudojamas, kai issikviesiu servisa
        List<SchoolClasses> schoolClassesList = new ArrayList<>();
        schoolClassesList.add(new SchoolClasses());
        // kai kviesiu servisa, tai paduosiu List<SchoolClasses>
        when(schoolClassesService.getSchoolChlassesList()).thenReturn(schoolClassesList);
//        // issikvieciam schoolClassesService.getSchoolChlassesList(), kad pasetintumem reiksme
//        List<SchoolClasses> allClasses = schoolClassesService.getSchoolChlassesList();

        List<Children> childrenList = new ArrayList<>();
        int childId = 1;
        String childName = "childName";
        String childSurname = "childSurname";
        Fosters fosters = new Fosters(1);
        SchoolClasses schoolClasses = new SchoolClasses(1);
        Users users = new Users(1);
        Children children = new Children(childId, childName, childSurname, fosters, schoolClasses, users);
        childrenList.add(children);
        when(childrenService.getAllChildren()).thenReturn(childrenList);
//        List<Children> allChildren = childrenService.getAllChildren();

        List<TeachersDTO> teachersDTOList = new ArrayList<>();
        teachersDTOList.add(new TeachersDTO());
        when(teacherService.getAllTeachers()).thenReturn(teachersDTOList);
//        List<TeachersDTO> allTeachers = teacherService.getAllTeachers();

        // mockMvc tikrinam, kai pasikreipaim i metoda
        mockMvc.perform(get("/schoolClassesMain"))
                // statusas turi buti ok
                .andExpect(status().isOk())
                // tikimes atsakymo i .jsp
                .andExpect(view().name("schoolClassesMain"))
                // tikrinam model atributo pagrazintas listas yra ne nulinis/ turi size
                .andExpect(model().attribute("allClasses", hasSize(1)))
                .andExpect(model().attribute("allChildren", hasSize(1)))
//                .andExpect(model().attribute("allChildren", hasItemInArray(hasProperty("childName", equalToIgnoringCase(childName)))))
//                .andExpect(model().attribute("allChildren", hasProperty("allChildren[0]", is(children))))
//                .andExpect(model().attribute("allChildren", Matchers.hasItemInArray(Matchers.<Children> hasProperty("childName", Matchers.equalToIgnoringCase(childName)))))
//                .andExpect(model().attribute("allChildren", hasProperty("allChildren", hasItem(hasProperty("childName", Matchers.equalTo(true))))))
                .andExpect(jsonPath("$[0].childId", is(1)))
                .andExpect(model().attribute("allTeachers", hasSize(1)));

        // Mockito verify tikrinam, kad nebutu servisas naudotas daugiau nei po karta
        verify(schoolClassesService, times(1)).getSchoolChlassesList();
        verify(childrenService, times(1)).getAllChildren();
        verify(teacherService, times(1)).getAllTeachers();
    }

    @Test
    public void saveAndFlushTeacher() throws Exception {

        int schoolClassesId = 1;
        String schoolClassesTitle = "schoolClassesTitle";
        int[] childId = {1, 2};
        int teacherId = 1;

        List<Children> childrenList = new ArrayList<>();
        childrenList.add(new Children(1));

        Teachers teachers = new Teachers();
        teachers.setTeacherId(teacherId);

        SchoolClasses schoolClasses = new SchoolClasses();
        schoolClasses.setSchoolClassesId(1);
        schoolClasses.setTitle("schoolClassTitle");
        schoolClasses.setTeacher(teachers);
        schoolClasses.setChildrenList(childrenList);

        when(schoolClassesService.findSchoolClassByTeacherId(teacherId)).thenReturn(schoolClasses);
//        SchoolClasses schoolClassByTeacherId = schoolClassesService.findSchoolClassByTeacherId(teacherId);
        when(schoolClassesService.updateSchoolClass(schoolClasses)).thenReturn(schoolClasses);
//        SchoolClasses schoolClasses1 = schoolClassesService.updateSchoolClass(schoolClassByTeacherId);
        when(schoolClassesService.updateSchoolClass(new SchoolClasses(schoolClassesId, schoolClassesTitle, teachers))).thenReturn(schoolClasses);
//        SchoolClasses schoolClasses2 = schoolClassesService.updateSchoolClass(new SchoolClasses(schoolClassesId, schoolClassesTitle, teachers));

        mockMvc.perform(post("/updateSchoolClass?schoolClassesId=1&title=schoolClassesTitle&childId=1&childId=2&teacherId=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:schoolClassesMain"));

    }

    @Test
    public void addTeacher() throws Exception {
    }

    @Test
    public void deleteClass() throws Exception {
    }

}