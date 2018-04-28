package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spring.model.User;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserDetailsManager userDetailsManager;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public RegistrationController(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    // empty string --> null
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            Model model) {

        String userName = user.getUserName();
        logger.info("Process registration form for: " + userName);

//        validation
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new User());
            model.addAttribute("registrationError", "User name/password can not be empty");
            logger.warning("User name/password can not be empty");
            return "registration-form";
        }

//        check if user already exists
        boolean userExists = userDetailsManager.userExists(userName);

        if (userExists) {
            model.addAttribute("user", new User());
            model.addAttribute("registrationError", "User name already exists");
            logger.warning("User name already exists");
            return "registration-form";
        }

        String encodedPassword = passwordEncoder.encode(userName);
        encodedPassword = "{bcrypt}" + encodedPassword;
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");

        org.springframework.security.core.userdetails.User tempUser;
        tempUser =
                new org.springframework.security.core.userdetails.User(userName, encodedPassword, authorities);

        userDetailsManager.createUser(tempUser);
        logger.info("Successfully created user: " + userName);

        return "registration-confirmation";
    }
}
