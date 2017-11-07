package lt.web.controler;

import lt.web.models.Children;
import lt.web.models.Fosters;
import lt.web.models.Users;
import lt.web.service.IChildrenService;
import lt.web.service.IFosterService;
import lt.web.service.IUserService;
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
public class FostersController {

    @Autowired
    IUserService userService;
    @Autowired
    IChildrenService childrenService;
    @Autowired
    IFosterService fosterService;


    @RequestMapping(value = "/fosterMain", method = RequestMethod.GET)
    public String welcome(Model model) {
        List<Fosters> allFosters = fosterService.getFostersList();
        model.addAttribute("allFosters", allFosters);

        List<Children> childrenList = childrenService.getAllChildren();
        model.addAttribute("childrenList", childrenList);

        return "fosterMain";
    }

    @RequestMapping(value = "/updateFoster", method = RequestMethod.POST)
    public String updateFoster(@RequestParam("fosterId") int fosterId
            , @RequestParam("name") String name
            , @RequestParam ("surname") String surname
            , @RequestParam ("phone") String phone
            , @RequestParam ("address") String address
            , @RequestParam ("childId") int[] childId
            , @RequestParam ("userId") int userId){

        for(int i = 0; i < childId.length; i++){
            childrenService.updateChildrensFoster(childId[i], fosterId);
        }
        fosterService.updateFoster(new Fosters(fosterId, name, surname, phone, address, new Users(userId)));

        return "redirect:fosterMain";
    }

    @RequestMapping(value = "/addFoster", method = RequestMethod.POST)
    public String addTeacher(@RequestParam ("name") String name
            , @RequestParam ("surname") String surname
            , @RequestParam ("address") String address
            , @RequestParam ("phone") String phone
            , @RequestParam ("childId") int[] childId
            , @RequestParam ("email") String email
            , @RequestParam ("password") String password){

        Users user = userService.saveUser(new Users(email, password));

        Fosters foster = fosterService.saveFoster(new Fosters(name, surname, phone, address, user));

        for(int i = 0; i < childId.length; i++){
            childrenService.updateChildrensFoster(childId[i], foster.getFosterId());
        }

        return "redirect:fosterMain";
    }

    @RequestMapping(value = "/deleteFoster", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFoster(@RequestParam("fosterId") int fosterId){
        userService.deleteUserByFosterId(fosterId);
        return "Istryne Foster";
    }

}
