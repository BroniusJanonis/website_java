package lt.web.controler;

import lt.web.models.Children;
import lt.web.models.Fosters;
import lt.web.models.SchoolClasses;
import lt.web.models.Users;
import lt.web.service.IChildrenService;
import lt.web.service.IFosterService;
import lt.web.service.ISchoolClassesService;
import lt.web.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class SchoolChildControllerTest {

    @Mock
    private IUserService userService;
    @Mock
    private IChildrenService childrenService;
    @Mock
    private ISchoolClassesService schoolClassesService;
    @Mock
    private IFosterService fosterService;
    @InjectMocks
    private SchoolChildController schoolChildController;
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(schoolChildController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void welcome() throws Exception {

//        List<Children> allChildren = childrenService.getAllChildren();
//        model.addAttribute("allChildren", allChildren);
//
//        List<SchoolClasses> classesList = schoolClassesService.getSchoolChlassesList();
//        model.addAttribute("classesList", classesList);
//
//        List<Fosters> fostersList = fosterService.getFostersList();
//        model.addAttribute("fostersList", fostersList);

        List<Children> childrenList = new ArrayList<>();
        int childId = 1;
        String name = "name";
        String surname = "surname";
        childrenList.add(new Children(childId, name, surname, new Fosters(), new SchoolClasses(), new Users()));
        when(childrenService.getAllChildren()).thenReturn(childrenList);

        List<SchoolClasses> schoolClassesList = new ArrayList<>();
        schoolClassesList.add(new SchoolClasses());
        when(schoolClassesService.getSchoolChlassesList()).thenReturn(schoolClassesList);

        List<Fosters> fostersList = new ArrayList<>();
        fostersList.add(new Fosters());
        when(fosterService.getFostersList()).thenReturn(fostersList);

        mockMvc.perform(get("/schoolChildMain"))
                .andExpect(status().isOk())
                .andExpect(view().name("schoolChildMain"))
                .andExpect(model().attribute("allChildren", hasSize(1)))
                .andExpect(model().attribute("classesList", hasSize(1)))
                .andExpect(model().attribute("fostersList", hasSize(1)))
                .andExpect(model().attribute("allChildren", hasItems(
                        allOf(
                                hasProperty("childId", is(childId)),
                                hasProperty("surname", is(surname)),
                                hasProperty("surname", is(surname))
                        )
                )));

        verify(childrenService, times(1)).getAllChildren();
        verify(schoolClassesService, times(1)).getSchoolChlassesList();
        verify(fosterService, times(1)).getFostersList();

    }

    @Test
    public void saveAndFlushTeacher() throws Exception {
        int childId = 1;
        String name = "name";
        String surname = "surname";
        int fosterId = 1;
        int schoolClassesId = 1;
        int userId = 1;

        childrenService.updateChildren(new Children(childId, name, surname, new Fosters(fosterId), new SchoolClasses(schoolClassesId), new Users(userId)));
        verify(childrenService, times(1)).updateChildren(Matchers.any());

        mockMvc.perform(post("/saveSchoolChild?childId=1&name=name&surname=surname&fosterId=1&schoolClassesId=1&userId=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:schoolChildMain"));
    }

    @Test
    public void addTeacher() throws Exception {
        String name = "name";
        String surname = "surname";
        int fosterId = 1;
        int schoolClassesId = 1;
        String email = "email";
        String password = "password";
        Users users = new Users(email, password);

        when(userService.saveUser(Matchers.any(Users.class))).thenReturn(users);
//        childrenService.saveChildren(new Children(name, surname, new Fosters(fosterId), new SchoolClasses(schoolClassesId), new Users(users.getUserId())));

        mockMvc.perform(post("/addChild?name=name&surname=surname&fosterId=1&fosterId=1&schoolClassesId=1&email=email&password=password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:schoolChildMain"));

        verify(userService, times(1)).saveUser(Matchers.any(Users.class));
        verify(childrenService, times(1)).saveChildren(Matchers.any(Children.class));

        assertEquals(userService.saveUser(users), users);

    }

    @Test
    public void deleteTeacher() throws Exception {
        int childId = 1;

        mockMvc.perform(post("/deleteChild?schoolChildId=1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUserByChildId(childId);

        assertEquals(schoolChildController.deleteTeacher(childId), "Istryne Child");
    }

}