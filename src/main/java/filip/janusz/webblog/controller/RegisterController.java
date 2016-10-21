package filip.janusz.webblog.controller;

import filip.janusz.webblog.domain.Role;
import filip.janusz.webblog.domain.validator.RecaptchaValidator;
import filip.janusz.webblog.domain.validator.UserValidator;
import filip.janusz.webblog.dto.UserDTO;
import filip.janusz.webblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private UserService userService;
    private UserValidator userValidator;
    private RecaptchaValidator recaptchaValidator;

    @Autowired
    public RegisterController(UserService userService,
                              UserValidator userValidator,
                              RecaptchaValidator recaptchaValidator) {

        this.userService = userService;
        this.userValidator = userValidator;
        this.recaptchaValidator = recaptchaValidator;
    }

    @InitBinder("user")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
        binder.addValidators(recaptchaValidator);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String getForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String createUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "register";
        }
        userDTO.setRole(Role.ROLE_USER);
        userService.createUser(userDTO);

        return "redirect:/login?registerOK";
    }
}
