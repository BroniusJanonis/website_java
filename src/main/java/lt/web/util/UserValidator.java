package lt.web.util;

import lt.web.models.Users;
import lt.web.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

// klase skirta patikrinti ar useris praeina validacijas arba meta klaida
// anotacijose toki utils irankiai yra aprasomi kaip kompotentai
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRep userRep;

    // kokia klase palaikom
    @Override
    public boolean supports(Class<?> aClass) {
        // musu pasirinkta klase palaiko aClass (Validacijos klase)
        // cia padarom, kad User klase pagrazintu true (jei return paliksime tik "true", tai visas klases priims validacijai, bet cia jau rizikuosime saugumu)
        return Users.class.equals(aClass);
    }

    // tikriname User, jei neatitinka taisykliu, tai turesim error. Tuomet per error pakisim raktazodi (pranesima vartotojui)
    // Object reikia gauti useri (reikes castint)
    @Override
    public void validate(Object ouser, Errors errors) {
        Users user = (Users) ouser;

        // SU USERNAME PARASOM:
        // pastoviai tikrina musu laukus Jsp ir ziuri ar visi atitinka. Jei nors vienam Jsp lauke "email" bus tuscias
        // , is karto mes klaida, kurios errorCode yra "EmptySpace" musu apsirasytas
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "EmptySpace");
        // Jeigu jau yra toks vartotojas ir reikia pranesti, jog toks egzistuoja
        // jei egzistuoja, tai negali registruoti, nes toks jau yra (jei nors viena irasa gaunam, tai jis jau nebe null, todel netenkins)
        if(userRep.findFirstByEmail(user.getEmail())!=null){
            // kuriam laukui irasom, tai prie "username", o "Warning.dublicate.email" yra errorCode
            errors.rejectValue("email", "Warning.dublicate.email");
        }
        // dabar patikrinam ilgi, nes buna, kad limitas 32 simboliai, o mes irasom ilgesni, tai serveris nukerpa ir palieka trumpesni
        // problema iskils, kai noresime logintis, o serveryje bus trumpesnis user ir nebus autentikacijos
        if(user.getEmail().length() <= 6 || user.getEmail().length() >= 32){
            errors.rejectValue("email", "Size.email");
        }

        // SU PASSWORD PARASOM:
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "EmptySpace");
        if(user.getPassword().length() <= 6 || user.getPassword().length() >= 32){
            errors.rejectValue("password", "Size.password");
        }
        // jei password ir confirm password keiciasi
        if(!user.getPassword_auth().equals(user.getPassword())){
            errors.rejectValue("password_auth", "Diff.passwordConfirm");
        }
    }
}
