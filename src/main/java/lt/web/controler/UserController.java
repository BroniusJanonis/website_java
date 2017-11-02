package lt.web.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.web.modelDTO.TeachersDTO;
import lt.web.models.Subjects;
import lt.web.models.Teachers;
import lt.web.models.Users;
import lt.web.service.*;
import lt.web.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@ComponentScan({"lt.web"})
public class UserController {

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private IUserService userService;
    @Autowired
    private ISecurityService securityService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    ISubjectService subjectService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        // paduosime tuscia useri ir tuomet ji uzregistruosim
        model.addAttribute("userForm", new Users());
        // ir graziname i jsp faila
        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    // "userForm" reikia, nes kitaip validation nesiuncia, nes mes registration.jsp esam pasisetine > modelAttribute="userForm"
    // BindingResult pasetins musu apsirasytu apsaugu (Validators) error ir pagrazins i musu .jsp errors (errors laukeliai .jsp faile apsirasyti)
    public String register(@ModelAttribute("userForm") Users userForm, BindingResult bindresult, Model model){
        // tikrinam is gautu Jsp duomenu ar atitinka validacijas (ilgio, simboliu, etc)
        // bindresult tai yra, koki mes error gausime is userValidator ir koki persiusime i Jsp
        userValidator.validate(userForm, bindresult);
        if(bindresult.hasErrors()){
//            model.addAttribute("BindingResult", bindresult);
            return "registration";
        }
        // iraso nauja useri. Apsirasem UserRoleServiceImp klaseje save su roles parinkimu (siuo atveju visas pridedam apsirase, o reiketu tik viena) ir password encoding
        userService.saveUser(userForm);
        // tikrina sauguma
        securityService.login(userForm.getEmail(), userForm.getPassword_auth());
        return "redirect:/welcomemainpage";
    }

    // ir /welcomemainpage ir / (tuscias) numeta i welcompage
    @RequestMapping(value = "/welcomemainpage", method = RequestMethod.GET)
    public String welcome(Model model) throws JsonProcessingException {
        List<TeachersDTO> allTeachers = teacherService.getAllTeachers();
        model.addAttribute("teachersList", allTeachers);

        List<Subjects> subjectsList = subjectService.findAll();
        Set<Subjects> subjectSet = new HashSet<>();
        subjectsList.stream().forEach(s -> {
            subjectSet.add(new Subjects(s.getSubjectId(), s.getSubjectName()));
        });
        model.addAttribute("subjectSet", subjectSet);
        subjectSet.iterator().next().getSubjectName();
        return "welcommainpage";
    }

    // cia pirma jungiasi per controleri "login". get'a
    // kadangi nesi prisijunges, tai nera klaidos, todel permeta i loginMain.jsp
    //    jei ivedant username ir password (apsirase loginMain.jsp), jie atitinka musus spring security
    //    , tai webSecurityConfigurator > .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/welcomemainpage")
    //    WebSecurityConfiguration tarp login ir success atliekam autorizacija, jei neveikia, tai bus error ir tuomet pagrazina i controleri
    //    Pagrazina i cotnroleri, kurio metodas ir value sutampa, turi buti "/login", kitu atveju, jei butu tarkim "/login1", tai nesugaudyti String error ir neperduotume i web
    //        kitu atveju, gavus ir spring security klaida (nes login spring suceess_, mums pagrazina error, kadangi tai nera lygu null, tai pagrazina i loginMain ir ismeta klaida
    //        , kuria atvaziduojam jau apsirasytam .jsp  (pirmu atveju tik pasijungus nemes klaidos, nes pirma karta uzkrauna puslapi per controleri, o jau jungiantis krauna "/login" per security pirma)
    // Svarbu "/login" turi sutapti su WebSecurityConfiguration, kad failure atveju nukreiptu i cia ir galetumem persiust "error" sunegeruota Spring Security
    // tikrina du kartus del pasikartojanciu dvieju Mapping'u
    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String login(Model model, String error){
        if(error!=null){
            model.addAttribute("error", "wrong username arba password");
        }
        return "loginMain";
    }


}
