package lt.web.controler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@ComponentScan({"lt.web"})
public class HeaderController {

    // "redirect:teacherMain" reikia idet ir kituose, kai pabaigsiu!!!!!!!!!!!!!!!!!!!!!!!!
    @RequestMapping(value = "/redirectTeacherWindow", method = RequestMethod.GET)
    public String teacherWindow(){
        return "redirect:teacherMain";
    }
    @RequestMapping(value = "/redirectSchoolChildWindow", method = RequestMethod.GET)
    public String schoolChildWindow(){
        return "redirect:schoolChildMain";
    }
    @RequestMapping(value = "/redirectSubjectsWindow", method = RequestMethod.GET)
    public String subjectWindow(){
        return "redirect:subjectMain";
    }

}
