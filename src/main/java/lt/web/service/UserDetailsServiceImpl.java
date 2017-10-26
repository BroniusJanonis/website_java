package lt.web.service;

import lt.web.models.Roles;
import lt.web.models.Users;
import lt.web.repository.RoleRep;
import lt.web.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRep userRep;
    @Autowired
    private RoleRep roleRep;

    @Override
    // Transaction readOnly, kad nerakintumem tranasactcijos (nesupratau iki galo)
    @Transactional(readOnly = true)
    // loadUserByUsername yra Spring security metodas, kuris isijungia per UserDetails (spring security klase) kiekvienu metu
    // , kai pasikreipiama i musu apsirasytus WebSecurityConfiguration.java puslapius
    // tuomet WebSecurityConfiguration.java > configureGlobal() tikrinam Autorizacija ar sutampa su is web ivestais ir tikrinam success or failure
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // gaunam useri (su Role(id) )
        Users user = userRep.findFirstByEmail(s);
        //gaunam Roles.roleTitle objekta pagal user.getRoles.getRolesId()
        String roleTitle = roleRep.findFirstByRoleId(user.getRole().getRoleId()).getRoleTitle();
        // Autorizacija. Pasetinam, kokia user role bus
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(roleTitle));
        // new org.springframework.security.core.userdetails. User, tai, koki tipa pagrazinsiu ir User() viduje pagrazinam autorizacija
        // pagrazinam username, password ir autorizacija
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
