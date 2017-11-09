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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@ComponentScan("lt.web")
public class SubjectsController {


    @Autowired
    ITeacherService teacherService;
    @Autowired
    ISubjectService subjectService;


    // ir /welcomemainpage ir / (tuscias) numeta i welcompage
    @RequestMapping(value = "/subjectMain", method = RequestMethod.GET)
    public String welcome(Model model){
        // I added connection of table incorrectly as I want web to show so I use DTO object (custom created) to parse current subjects and put in order as I want
        // instead of Subjects (int subjectId; String subjectName; Teachers teacher; ), i use SubjectTeacherListDTO (int subjectId; String subjectName; List<Teachers> teacher; )

        // Subjects list
        List<Subjects> subjectServiceAll = subjectService.findAll();
        // get only unique Subject Map
        Map<String, Subjects> subjectsMap = new HashMap<>();
        for(Subjects subjects: subjectServiceAll){
            subjectsMap.put(subjects.getSubjectName(), subjects);
        }
        // New List of Subjects with Teacher list in it
        List<SubjectTeacherListDTO> sublist = new ArrayList<>();
        // parse Map
        subjectsMap.forEach((k, v) ->{
            // TeachersId of Each subject
            List<Integer> teachersListBySubjectName = subjectService.getTeachersListBySubjectName(k);

            // List of Teachers of one subject
            List<Teachers> allByTeacherId = new ArrayList<>();
            for(Integer teacherId: teachersListBySubjectName){
                // Get Teachers by got integer and put in Teachers list
                Teachers byTeacherId = teacherService.findByTeacherId(teacherId);
                allByTeacherId.add(byTeacherId);
            }
            SubjectTeacherListDTO subjectTeacherListDTO = new SubjectTeacherListDTO(v.getSubjectId(), v.getSubjectName(), allByTeacherId);
            sublist.add(subjectTeacherListDTO);
        });
        model.addAttribute("subjectsList", sublist);

        List<TeachersDTO> allTeachers = teacherService.getAllTeachers();
        model.addAttribute("allTeachers", allTeachers);

        return "subjectMain";
    }

    @RequestMapping(value = "/updateSubject", method = RequestMethod.POST)
    public String updateSubject(@RequestParam("subjectId") int subjectId
            , @RequestParam ("subjectName") String subjectName
            , @RequestParam ("teacherId") int[] teacherId){
        // if list is empty, set Subjects tescherId to null but keep subjects
        if (teacherId[0] == 0) {
            // all teacherId from Subjects with subjectName will be set to null
            List<Subjects> bySubjectName = subjectService.findBySubjectName(subjectName);
            for(Subjects subject: bySubjectName){
                subjectService.updateSubjectByTeacherId(subject.getSubjectId(), subject.getSubjectName());
            }
            return "redirect:subjectMain";
        }
        // if we get chosen teachers for subject, we delete all subjects with subject name > and then create new subjects with subjectName and teacher's connection
        subjectService.deleteSubjectsBySubjectName(subjectName);
        for(Integer teacher: teacherId){
            subjectService.save(subjectName, new Teachers(teacher));
        }

        return "redirect:subjectMain";
    }

    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
    public String addSubject(@RequestParam ("subjectName") String subjectName){
        subjectService.saveSubjectWithSubjectNameOnly(subjectName);
        return "redirect:subjectMain";
    }

    @RequestMapping(value = "/deleteSubject", method = RequestMethod.POST)
    @ResponseBody
    public String deleteClass(@RequestParam("subjectId") int subjectId){
        subjectService.deleteSubjectsBySubjectId(subjectId);
        return "Istryne Klase su jo user, klase, subjects";
    }

}


