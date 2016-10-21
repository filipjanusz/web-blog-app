package filip.janusz.webblog.controller;

import filip.janusz.webblog.domain.User;
import filip.janusz.webblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

    private UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{username}")
    public String showProfile(@PathVariable("username") String username, Model model) {
        User user = userService.findUserByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }

}
