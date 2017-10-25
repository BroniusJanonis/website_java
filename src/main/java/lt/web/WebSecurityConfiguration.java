package lt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    // nezinau, del ko Autowired neveikia, tai galima per Bean tiesiai is konfiguracijos pakurti nauja objekta encoderio
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    };

    // jei autorizacija nepatvirtinta per public void configureGlobal, tai kur numesti (i pagridnini ar error, ar registruotis per naujo)
    protected void configure(HttpSecurity http) throws Exception{
        // kuriuos psl praleidziame (nes tik login ir register langus prileidziame [controleriu] neautorizavus)
        // Taip pat praleidziam ir Css, JavaScript source ar pan
        // /resources/** < /** reiskia, kad viska
        http.authorizeRequests().antMatchers("/resources/**", "/register").permitAll()
                .anyRequest().authenticated()
                // apsirasom, koki duomenys vaikscios is formLogin
                // , nes getUserByUsername is DetailServices laukia username, o ne email ir meta null
                .and()
                .formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                // jei dar turim papildomu salygu, tai su and() pridedame
                .and()
                // login page, cia spring security. Reiskia, kad, kai kreipsis i "/login", tai nukreips pirma i musu spring security autoracijos login
                .formLogin().loginPage("/login").permitAll()
                // cia nukreipiam, kai success (autorizacija patvirtinta per configureGlobal [apacioje apsirase])
                .defaultSuccessUrl("/welcomemainpage")  //< testuojam
                  // on failure galime numeski i bet kuri kita jsp, tarkim vel i login langa.
                  // Siuo atveju uzkomentuojam .failureUrl("/login"), nes mes apsirase controleryje, jog on, kai security login failure, tai permeta i musu controlerio "/login"
                  // , o ten mes sugriebiam is security  gauta error bei si persiunciam atgal controleryje per return i login.jsp langa, bet si karta jau su error (error apsirase esam webe)
                  // .failureUrl("/login")
                // logout() pagrazina i login langa arba gal galim apsirasyti savo logout langa?
                .and().logout().permitAll()
                // login ir logout yra spring main metodai, jei atsijungi su logout, tai pagrazina i login > kas yra mano loginMain.jsp
                .logoutSuccessUrl("/");
        // praleidziame resourses visus, tada register langa, tada login ir tada nutraukiam viska
    }

    // atkoduojame duomenis is DB, kad globaliai sifruotu (patikrina ar nera prisijungusio vartotojo)
    // Jungiantis per weba, kuris siuncia duomenis i "/login"
    // , pirma security, pagal gauta useri ir password, tikrina ar useris jau yra sistemoje ir ar jo atkoduotas password'as is DB atitinka webe suvesta
    // Jei succes, tai leidzia toliau eit, kaip apsirase "protected void configure()" .defaultSuccessUrl
    // Jei failure, tai atiduodam handlint controlerio metodui "/login" arba galim securityje cia jau apsirasyt
    // AuthenticationManagerBuilder, kaip supratau, yra atsakingas tikrinimo tarp UserDetailsService ir is web ivestu duomenu
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
