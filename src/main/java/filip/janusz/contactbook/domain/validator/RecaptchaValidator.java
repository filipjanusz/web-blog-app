package filip.janusz.contactbook.domain.validator;

import filip.janusz.contactbook.dto.RecaptchaDTO;
import filip.janusz.contactbook.dto.UserDTO;
import filip.janusz.contactbook.service.RecaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RecaptchaValidator implements Validator {

    private RecaptchaService recaptchaService;

    @Autowired
    public RecaptchaValidator(RecaptchaService recaptchaService) {
        this.recaptchaService = recaptchaService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RecaptchaDTO recaptcha = (RecaptchaDTO) target;
        if(recaptcha.getRecaptchaResponse() != null &&
                !recaptcha.getRecaptchaResponse().isEmpty() &&
                !recaptchaService.isResponseValid(recaptcha.getRecaptchaResponse())) {
            errors.reject("recaptcha.error", "Recaptcha is invalid");
        }
    }
}
