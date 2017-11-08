package lt.web.controler;

import lt.web.modelDTO.SubjectTeacherListDTO;
import lt.web.modelDTO.TeachersDTO;
import lt.web.models.Subjects;
import lt.web.models.Teachers;
import lt.web.service.ISubjectService;
import lt.web.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@ComponentScan("lt.web")
public class SubjectsController {

//    @Autowired
//    ISchoolClassesService schoolClassesService;
//    @Autowired
//    IChildrenService childrenService;
    @Autowired
    ITeacherService teacherService;
    @Autowired
    ISubjectService subjectService;


    // ir /welcomemainpage ir / (tuscias) numeta i welcompage
    @RequestMapping(value = "/subjectMain", method = RequestMethod.GET)
    public String welcome(Model model){

        List<Subjects> subjectServiceAll = subjectService.findAll();
        Set<SubjectTeacherListDTO> sublist = new HashSet<>();
        for(Subjects sub: subjectServiceAll){
            List<Integer> teachersListBySubjectName = subjectService.getTeachersListBySubjectName(sub.getSubjectName());
            List<Teachers> allByTeacherId = new ArrayList<>();
            for(Integer teacherId: teachersListBySubjectName){
                Teachers byTeacherId = teacherService.findByTeacherId(teacherId);
                allByTeacherId.add(byTeacherId);
            }
            SubjectTeacherListDTO subjectTeacherListDTO = new SubjectTeacherListDTO(sub.getSubjectId(), sub.getSubjectName(), allByTeacherId);
            sublist.add(subjectTeacherListDTO);
        }
        model.addAttribute("subjectsList", sublist);

        List<TeachersDTO> allTeachers = teacherService.getAllTeachers();
        model.addAttribute("allTeachers", allTeachers);

        return "subjectMain";
    }

//    @RequestMapping(value = "/updateSchoolClass", method = RequestMethod.POST)
//    public String saveAndFlushTeacher(@RequestParam("schoolClassesId") int schoolClassesId
//            , @RequestParam ("title") String schoolClassesTitle
//            , @RequestParam("childId") int[] childId
//            , @RequestParam ("teacherId") int teacherId){
//        SchoolClasses schoolClassByTeacherId = schoolClassesService.findSchoolClassByTeacherId(teacherId);
//        Teachers teacher = new Teachers(teacherId);
//        if(schoolClassByTeacherId != null){
//            schoolClassByTeacherId.setTeacher(null);
//            schoolClassesService.updateSchoolClass(schoolClassByTeacherId);
//        } else if(teacherId == 0){
//            teacher = null;
//        }
//        schoolClassesService.updateSchoolClass(new SchoolClasses(schoolClassesId, schoolClassesTitle, teacher));
//        for (Integer id: childId) {
//            childrenService.updateChildrenByClassId(schoolClassesId, id);
//        }
//        return "redirect:schoolClassesMain";
//    }
//
//    @RequestMapping(value = "/addClasses", method = RequestMethod.POST)
//    public String addTeacher(@RequestParam ("title") String schoolClassesTitle){
//        schoolClassesService.saveClassesTitle(schoolClassesTitle);
//        return "redirect:schoolClassesMain";
//    }
//
//    @RequestMapping(value = "/deleteClass", method = RequestMethod.POST)
//    @ResponseBody
//    public String deleteClass(@RequestParam("classId") int classId){
//        schoolClassesService.deleteSchoolclassesByClassId(classId);
//        return "Istryne Klase su jo user, klase, subjects";
//    }
//}

}


