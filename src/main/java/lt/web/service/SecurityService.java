package lt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements ISecurityService{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // login'ui skirta validacijai. Naudojam UserController "/register" metoda, kad patikrinam ar irasytas useris atitinka prisijungimo duomenis
    // Info ateina is frontendo
    public void login(String email, String password_auth) {
        // UserDetailsServiceImpl klaseje apsirasem kaip loadUserByUsername gauname pagal email. tikrinam DB esanti objekta (atkoduota) su email, password, role
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        // headeriuose (puslapio) eina Tokenai, per kuriuos eina autorizacija (per Tokenus sifruojama informacija)
        // pasiimam userdetail name, pasq, ir userdetails (roliu seta) bei pridedam dar is webo gauta passw
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password_auth, userDetails.getAuthorities());
        // autentikuojames. Tikrinam LoadUserByUsename( gaula per web ivesta username objekta[viduje username, password, role]) su ivestu per web passw, jei atitinka, tesiam
        // , kitu atveju error ir WebScurityConfiguration.java permeta mus i logina langa bei UserController.java permeta zinute error
        // tiek usernamePasswordAuthenticationToken, tiek authenticationManager.authenticate(usernamePasswordAuthenticationToken); yra autorizuoti, tik authenticationManager
        // , jei supratau teisingai, atkoduoja usernamePasswordAuthenticationToken esanti userDetails password ir sulygina su ivestu is web, jei sutinka, tuomet istrina is token
        // , bet palieka isAuthorized (kas buvo ir pries tai). Tuomet, kad ir kitas atkoduotu token'a, ten butu tik autorizacija, bet nebutu jokio password'o
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        // kai autentikavomes, tai patirkinam ar vartotojas autentikuotas
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            // jei autentikuotas, tai tikrinam
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
}
