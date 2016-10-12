package filip.janusz.contactbook.controller;

import filip.janusz.contactbook.domain.User;
import filip.janusz.contactbook.domain.validator.UserValidator;
import filip.janusz.contactbook.dto.UserDTO;
import filip.janusz.contactbook.pagination.PageWrapper;
import filip.janusz.contactbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;
    private UserValidator userValidator;

    @Autowired
    public AdminController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @InitBinder("user")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allUsers")
    public String listAllUsers(Model model, Pageable pageable) {
        PageWrapper<User> pageWrapper = new PageWrapper<>(userService.findAllUsersOrderByUsername(pageable), "/admin/allUsers");
        model.addAttribute("page", pageWrapper);
        model.addAttribute("users", pageWrapper.getContent());

        model.addAttribute("user", new UserDTO());

        return "allUsers";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public String addUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "allUsers";
        }
        userService.createUser(userDTO);
        return "redirect:/admin/allUsers";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") String userId) {
        userService.deleteUserById(userId);
        return "redirect:/admin/allUsers";
    }
}
