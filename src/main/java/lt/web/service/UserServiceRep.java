package lt.web.service;

import lt.web.models.Roles;
import lt.web.models.Users;
import lt.web.repository.RoleRep;
import lt.web.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// Service anotacija, skirta User Services Repository (irasymams ir modifikacijai su User
@Service
public class UserServiceRep {

    @Autowired
    UserRep userRep;
    @Autowired
    RoleRep roleRep;
    // Encoder'is, uzkoduoti password, jog duomenu bazeje nefiguruotu grynas tekstas. Ji jau apsirese esam WebSecyruttConfiguration
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(Users userForm) {
        // kadangi esam bCryptPasswordEncoder apsirase WebSecyruttConfiguration klaseje, kuris priima user, tai uzkoduojame
        userForm.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        userForm.setRole(roleRep.findFirstByRoleId(1));  // duok visus ir prisiskiriam i HashSet
        // kadangi jau @autowirine esamt UserRep, tai ten yra spring JPA implementuoti, kuris duoda savo metoda save()
        userRep.save(userForm);
    }

}
