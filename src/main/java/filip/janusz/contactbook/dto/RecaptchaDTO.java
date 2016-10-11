package filip.janusz.contactbook.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public abstract class RecaptchaDTO {

    @NotEmpty(message = "Fill out recaptcha field")
    @JsonProperty("g-recaptcha-response")
    private String recaptchaResponse;

    public String getRecaptchaResponse() {
        return recaptchaResponse;
    }

    public void setRecaptchaResponse(String recaptchaResponse) {
        this.recaptchaResponse = recaptchaResponse;
    }
}
