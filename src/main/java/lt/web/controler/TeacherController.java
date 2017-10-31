package lt.web.controler;

import lt.web.models.SchoolClasses;
import lt.web.models.Subjects;
import lt.web.models.Teachers;
import lt.web.models.Users;
import lt.web.service.ITeacherService;
import lt.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@ComponentScan({"lt.web"})
public class TeacherController {

    @Autowired
    ITeacherService teacherService;
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/saveAndFlushTeacher", method = RequestMethod.POST)
    public String saveAndFlushTeacher(@RequestParam ("name") String name
                                    , @RequestParam ("surname") String surname
                                    , @RequestParam ("phone") String phone
                                    , @RequestParam ("schoolClassesTitle") String schoolClassesTitle
                                    , @RequestParam ("schoolClassesId") int schoolClassesId
                                    , @RequestParam ("teacherId") int teacherId
                                    , @RequestParam ("userId") int userId
                                    , @RequestParam ("subjectName") String[] subjectName
                                    , @RequestParam ("subjectId") int[] subjectId){
        List<Subjects> subjectsList = new ArrayList<>();
        for(int i = 0; i < subjectId.length; i++){
            Subjects subject = new Subjects(subjectId[i], subjectName[i]);
            subjectsList.add(subject);
        }
//        Teachers teachers = new Teachers(teacherId, name, surname, phone, subjectsList);
        Teachers teachers = new Teachers(teacherId, name, surname, phone, subjectsList, userService.firstByUserId(userId));
        teacherService.saveTeacher(teachers);
        return "redirect:welcomemainpage";
    }
}
