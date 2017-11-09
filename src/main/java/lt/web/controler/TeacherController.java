package lt.web.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import lt.web.modelDTO.TeachersDTO;
import lt.web.models.*;
import lt.web.service.ISchoolClassesService;
import lt.web.service.ISubjectService;
import lt.web.service.ITeacherService;
import lt.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@ComponentScan({"lt.web"})
public class TeacherController {

    @Autowired
    ITeacherService teacherService;
    @Autowired
    IUserService userService;
    @Autowired
    ISchoolClassesService schoolClassesService;
    @Autowired
    ISubjectService subjectService;

    // ir /welcomemainpage ir / (tuscias) numeta i welcompage
    @RequestMapping(value = "/teacherMain", method = RequestMethod.GET)
    public String welcome(Model model) throws JsonProcessingException {
        List<TeachersDTO> allTeachers = teacherService.getAllTeachers();
        model.addAttribute("teachersList", allTeachers);

        List<Subjects> subjectsList = subjectService.findAll();
        Set<String> subjectSet = new HashSet<>();
        subjectsList.stream().forEach(s -> {
            subjectSet.add(s.getSubjectName());
        });
        model.addAttribute("subjectSet", subjectSet);
        return "teacherMain";
    }

//    @RequestMapping(value = "/saveAndFlushTeacher", method = RequestMethod.POST)
//    public String saveAndFlushTeacher(@RequestParam ("name") String name
//                                    , @RequestParam ("surname") String surname
//                                    , @RequestParam ("phone") String phone
//                                    , @RequestParam ("schoolClassesTitle") String schoolClassesTitle
//                                    , @RequestParam ("schoolClassesId") int schoolClassesId
//                                    , @RequestParam ("teacherId") int teacherId
//                                    , @RequestParam ("userId") int userId
//                                    , @RequestParam ("subjectName") String[] subjectName
//                                    , @RequestParam ("subjectId") int[] subjectId){
//        List<Subjects> subjectsList = new ArrayList<>();
//        for(int i = 0; i < subjectId.length; i++){
//            int ii = subjectService.setSubjectById(subjectName[i], teacherId, subjectId[i]);
//            Subjects subjects = new Subjects(subjectId[i], subjectName[i]);
//            subjectsList.add(subjects);
//        }
////        Teachers teachers = new Teachers(teacherId, name, surname, phone, subjectsList);
//        schoolClassesService.setTitleById(schoolClassesTitle, teacherId, schoolClassesId);
//        Teachers teachers = new Teachers(teacherId, name, surname, phone, subjectsList, userService.firstByUserId(userId));
//        teacherService.saveTeacher(teachers);
//        return "redirect:teacherMain";
//    }
//
//    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
//    public String addTeacher(@RequestParam ("name") String name
//            , @RequestParam ("surname") String surname
//            , @RequestParam ("phone") String phone
//            , @RequestParam ("schoolClassesTitle") String schoolClassesTitle
//            , @RequestParam ("subjectName") String[] subjectName
//            , @RequestParam ("email") String email
//            , @RequestParam ("password") String password){
//        Users users = userService.saveUser(new Users(email, password));
//        Teachers teachers = new Teachers(name, surname, phone, users);
//        Teachers teacher = teacherService.saveTeacher(teachers);
//        List<Subjects> subjectsList = new ArrayList<>();
//        for(int i = 0; i < subjectName.length; i++){
//            Subjects subject = subjectService.save(subjectName[i], teacher);
//            subjectsList.add(subject);
//        }
//        SchoolClasses schoolClasses = schoolClassesService.save(schoolClassesTitle, teacher);
//        return "redirect:teacherMain";
//    }
//
//    @RequestMapping(value = "/deleteTeacher", method = RequestMethod.POST)
//    @ResponseBody
//    public String deleteTeacher(@RequestParam("teacherId") int teacherId){
////       schoolClassesService.deleteSchoolclassesByTeacherId(teacherId);
//       userService.deleteUserByTeacherId(teacherId);
////       teacherService.deleteTeacher(teacherId);
//        return "Istryne Teacher su jo user, klase, subjects";
//    }

}
