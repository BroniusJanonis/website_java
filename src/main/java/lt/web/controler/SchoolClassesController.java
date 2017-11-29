package lt.web.controler;

import lt.web.modelDTO.TeachersDTO;
import lt.web.models.Children;
import lt.web.models.SchoolClasses;
import lt.web.models.Teachers;
import lt.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ComponentScan("lt.web")
public class SchoolClassesController {


    @Autowired
    ISchoolClassesService schoolClassesService;
    @Autowired
    IChildrenService childrenService;
    @Autowired
    ITeacherService teacherService;


    // ir /welcomemainpage ir / (tuscias) numeta i welcompage
    @RequestMapping(value = "/schoolClassesMain", method = RequestMethod.GET)
    public String welcome(Model model){
        List<SchoolClasses> allClasses = schoolClassesService.getSchoolChlassesList();
        model.addAttribute("allClasses", allClasses);

        List<Children> allChildren = childrenService.getAllChildren();
        model.addAttribute("allChildren", allChildren);

        List<TeachersDTO> allTeachers = teacherService.getAllTeachers();
        model.addAttribute("allTeachers", allTeachers);

        return "schoolClassesMain";
    }

    @RequestMapping(value = "/updateSchoolClass", method = RequestMethod.POST)
    public String saveAndFlushTeacher(@RequestParam("schoolClassesId") int schoolClassesId
            , @RequestParam (value = "title") String schoolClassesTitle
            , @RequestParam("childId") int[] childId
            , @RequestParam ("teacherId") int teacherId){
        SchoolClasses schoolClassByTeacherId = schoolClassesService.findSchoolClassByTeacherId(teacherId);
        Teachers teacher = new Teachers(teacherId);
        if(schoolClassByTeacherId != null){
            schoolClassByTeacherId.setTeacher(null);
            schoolClassesService.updateSchoolClass(schoolClassByTeacherId);
        } else if(teacherId == 0){
            teacher = null;
        }
        schoolClassesService.updateSchoolClass(new SchoolClasses(schoolClassesId, schoolClassesTitle, teacher));
        for (Integer id: childId) {
            childrenService.updateChildrenByClassId(schoolClassesId, id);
        }
        return "redirect:schoolClassesMain";
    }

    @RequestMapping(value = "/addClasses", method = RequestMethod.POST)
    public String addTeacher(@RequestParam ("title") String schoolClassesTitle){
        schoolClassesService.saveClassesTitle(schoolClassesTitle);
        return "redirect:schoolClassesMain";
    }

    @RequestMapping(value = "/deleteClass", method = RequestMethod.POST)
    @ResponseBody
    public String deleteClass(@RequestParam("classId") int classId){
        schoolClassesService.deleteSchoolclassesByClassId(classId);
        return "Istryne Klase su jo user, klase, subjects";
    }
}
