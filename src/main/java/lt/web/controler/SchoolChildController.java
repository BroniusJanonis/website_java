package lt.web.controler;

import lt.web.models.Children;
import lt.web.models.Fosters;
import lt.web.models.SchoolClasses;
import lt.web.models.Users;
import lt.web.service.IChildrenService;
import lt.web.service.IFosterService;
import lt.web.service.ISchoolClassesService;
import lt.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@ComponentScan("lt.web")
public class SchoolChildController {

    @Autowired
    IUserService userService;
    @Autowired
    IChildrenService childrenService;
    @Autowired
    ISchoolClassesService schoolClassesService;
    @Autowired
    IFosterService fosterService;


    @RequestMapping(value = "/schoolChildMain", method = RequestMethod.GET)
    public String welcome(Model model) {
        List<Children> allChildren = childrenService.getAllChildren();
        model.addAttribute("allChildren", allChildren);

        List<SchoolClasses> classesList = schoolClassesService.getSchoolChlassesList();
        model.addAttribute("classesList", classesList);

        List<Fosters> fostersList = fosterService.getFostersList();
        model.addAttribute("fostersList", fostersList);

        return "schoolChildMain";
    }

    @RequestMapping(value = "/saveSchoolChild", method = RequestMethod.POST)
    public String saveAndFlushTeacher(@RequestParam("childId") int childId
            , @RequestParam("name") String name
            , @RequestParam ("surname") String surname
            , @RequestParam ("fosterId") int fosterId
            , @RequestParam ("schoolClassesId") int schoolClassesId
            , @RequestParam ("userId") int userId){
        childrenService.updateChildren(new Children(childId, name, surname, new Fosters(fosterId), new SchoolClasses(schoolClassesId), new Users(userId)));
        return "redirect:schoolChildMain";
    }

    @RequestMapping(value = "/addChild", method = RequestMethod.POST)
    public String addTeacher(@RequestParam ("name") String name
            , @RequestParam ("surname") String surname
            , @RequestParam ("fosterId") int fosterId
            , @RequestParam ("schoolClassesId") int schoolClassesId
            , @RequestParam ("email") String email
            , @RequestParam ("password") String password){
        Users users = userService.saveUser(new Users(email, password));
        childrenService.saveChildren(new Children(name, surname, new Fosters(fosterId), new SchoolClasses(schoolClassesId), new Users(users.getUserId())));
        return "redirect:schoolChildMain";
    }

//    @RequestMapping(value = "/deleteTeacher", method = RequestMethod.POST)
//    @ResponseBody
//    public String deleteTeacher(@RequestParam("teacherId") int teacherId){
////       schoolClassesService.deleteSchoolclassesByTeacherId(teacherId);
//        userService.deleteUserByTeacherId(teacherId);
////       teacherService.deleteTeacher(teacherId);
//        return "Istryne Teacher su jo user, klase, subjects";
//    }

}
