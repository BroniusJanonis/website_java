package lt.web.service;

import lt.web.models.Users;
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

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRep userRep;

    @Override
    // Transaction readOnly, kad nerakintumem tranasactcijos (nesupratau iki galo)
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userRep.findByEmail(s);
        // Autorizacija. Pasetinam, kokia user role bus
        GrantedAuthority grantedAuthorities = new SimpleGrantedAuthority(user.getRole().getRoleTitle());
        // new org.springframework.security.core.userdetails. User, tai, koki tipa pagrazinsiu ir User() viduje pagrazinam autorizacija
        // pagrazinam username, password ir autorizacija
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), (Collection<? extends GrantedAuthority>) grantedAuthorities);
    }
}
