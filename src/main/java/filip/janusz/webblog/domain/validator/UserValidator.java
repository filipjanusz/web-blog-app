package filip.janusz.webblog.domain.validator;


import filip.janusz.webblog.dto.UserDTO;
import filip.janusz.webblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        validatePassword(userDTO, errors);
        validateUsername(userDTO, errors);
    }

    private void validatePassword(UserDTO userDTO, Errors errors) {
        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            errors.reject("password.no_match", "Password do not match");
        }
    }

    private void validateUsername(UserDTO userDTO, Errors errors) {
        if(userService.checkIfUserExist(userDTO.getUsername())) {
            errors.reject("username.exists", "Username already exists");
        }
    }
}
