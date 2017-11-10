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

        List<SchoolClasses> schoolChlassesList = schoolClassesService.getSchoolChlassesList();
        model.addAttribute("schoolChlassesList", schoolChlassesList);

        return "teacherMain";
    }

    @RequestMapping(value = "/updateTeacher", method = RequestMethod.POST)
    public String saveAndFlushTeacher(@RequestParam ("name") String name
                                    , @RequestParam ("surname") String surname
                                    , @RequestParam ("phone") String phone
                                    , @RequestParam ("schoolClassesId") int schoolClassesId
                                    , @RequestParam ("teacherId") int teacherId
                                    , @RequestParam ("userId") int userId
                                    , @RequestParam ("subjectName") String[] subjectName) {

        // if subject notmarket delete subject
        // else remove subjects by teacherId and add new subjects by teacherId and subjectName
        subjectService.deleteSubjectsByTeacherId(teacherId);
        if (subjectName.length != 0) {
            for (String subjName : subjectName) {
                subjectService.save(subjName, new Teachers(teacherId));
            }
        }

        // we get schoolclasses by teacherId and SchoolClassesId
        SchoolClasses schoolClassByschoolClassesId = schoolClassesService.findSchoolClassByschoolClassesId(schoolClassesId);
        SchoolClasses schoolClassByTeacherId = schoolClassesService.findSchoolClassByTeacherId(teacherId);

        // if schoolClass by TeacherId exist
        if(schoolClassByTeacherId != null){
            // nullify teacherId
            schoolClassByTeacherId.setTeacher(null);
            schoolClassesService.updateSchoolClass(schoolClassByTeacherId);
            // update teacherId to new schoolclass
            schoolClassesService.updateSchoolClassByTeacherIdAndSchoolClassId(teacherId, schoolClassesId);
        }
        // else if SchoolClass by schoolclassId exist
        else if(schoolClassByschoolClassesId != null){
            // if that class does not have teacher
            if(schoolClassByschoolClassesId.getTeacher() == null){
                schoolClassByschoolClassesId.setTeacher(new Teachers(teacherId));
                schoolClassesService.updateSchoolClass(schoolClassByschoolClassesId);
                // else if that class has teacher
            }else if (schoolClassByschoolClassesId.getTeacher() != null){
                schoolClassByschoolClassesId.setTeacher(null);
                schoolClassesService.updateSchoolClass(schoolClassByschoolClassesId);
            }
        }


        // update teacher
        teacherService.saveTeacher(new Teachers(teacherId, name, surname, phone, new Users(userId)));

        return "redirect:teacherMain";
    }

//    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
//    public String addTeacher(@RequestParam ("name") String name
//            , @RequestParam ("surname") String surname
//            , @RequestParam ("phone") String phone
//            , @RequestParam ("schoolClassesId") int schoolClassesId
//            , @RequestParam ("subjectId") int[] subjectId
//            , @RequestParam ("email") String email
//            , @RequestParam ("password") String password){
//        Users users = userService.saveUser(new Users(email, password));
//        Teachers teachers = new Teachers(name, surname, phone, users);
//        Teachers teacher = teacherService.saveTeacher(teachers);
//
//        List<Subjects> subjectsList = new ArrayList<>();
//        for(int i = 0; i < subjectId.length; i++){
//            Subjects bySubjectId = subjectService.findBySubjectId(subjectId[i]);
//            Subjects subject = subjectService.save(bySubjectId.getSubjectName(), teacher);
//            subjectsList.add(subject);
//        }
//        schoolClassesService.updateSchoolClassByTeacherIdAndSchoolClassId(teacher.getTeacherId(), schoolClassesId);
//        return "redirect:teacherMain";
//    }

    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    public String addTeacher(@RequestParam ("name") String name
            , @RequestParam ("surname") String surname
            , @RequestParam ("phone") String phone
            , @RequestParam ("schoolClassesTitle") String schoolClassesTitle
            , @RequestParam ("subjectName") String[] subjectName
            , @RequestParam ("email") String email
            , @RequestParam ("password") String password){
        Users users = userService.saveUser(new Users(email, password));
        Teachers teachers = new Teachers(name, surname, phone, users);
        Teachers teacher = teacherService.saveTeacher(teachers);
        List<Subjects> subjectsList = new ArrayList<>();
        for(int i = 0; i < subjectName.length; i++){
            Subjects subject = subjectService.save(subjectName[i], teacher);
            subjectsList.add(subject);
        }
        SchoolClasses schoolClasses = schoolClassesService.save(schoolClassesTitle, teacher);
        return "redirect:teacherMain";
    }

//    @RequestMapping(value = "/deleteTeacher", method = RequestMethod.POST)
//    @ResponseBody
//    public String deleteTeacher(@RequestParam("teacherId") int teacherId){
////       schoolClassesService.deleteSchoolclassesByTeacherId(teacherId);
//       userService.deleteUserByTeacherId(teacherId);
////       teacherService.deleteTeacher(teacherId);
//        return "Istryne Teacher su jo user, klase, subjects";
//    }

}
