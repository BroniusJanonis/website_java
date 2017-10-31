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
public class UserService implements IUserService{

    @Autowired
    private UserRep userRep;
    @Autowired
    private RoleRep roleRep;
    // Encoder'is, uzkoduoti password, jog duomenu bazeje nefiguruotu grynas tekstas. Ji jau apsirese esam WebSecyruttConfiguration
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUser(Users userForm) {
        Roles role = new Roles();
        role.setRoleId(roleRep.findFirstByRoleId(1).getRoleId());
        // kadangi esam bCryptPasswordEncoder apsirase WebSecyruttConfiguration klaseje, kuris priima user, tai uzkoduojame
        userForm.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        userForm.setRole(role);  // duok visus ir prisiskiriam i HashSet
        // kadangi jau @autowirine esamt UserRep, tai ten yra spring JPA implementuoti, kuris duoda savo metoda save()
        userRep.save(userForm);
    }

    @Override
    public Users firstByUserId(int id) {
        Users firstByUserId = userRep.findFirstByUserId(id);
        return firstByUserId;
    }

}
