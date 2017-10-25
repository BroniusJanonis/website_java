package lt.web.controler;

import lt.web.models.Users;
import lt.web.repository.UserRep;
import lt.web.service.SecurityService;
import lt.web.service.UserServiceRep;
import lt.web.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    UserValidator userValidator;
    @Autowired
    UserServiceRep userServiceRep;
    @Autowired
    SecurityService securityService;

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
        userServiceRep.saveUser(userForm);
        // tikrina sauguma
        securityService.login(userForm.getEmail(), userForm.getPassword_auth());
        return "redirect:/welcomemainpage";
    }

    // ir /welcomemainpage ir / (tuscias) numeta i welcompage
    @RequestMapping(value = "/welcomemainpage", method = RequestMethod.GET)
    public String welcome(Model model){
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
    // Svarbu
    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String login(Model model, String error){
        if(error!=null){
            model.addAttribute("error", "wrong username arba password");
        }
        return "loginMain";
    }
}
