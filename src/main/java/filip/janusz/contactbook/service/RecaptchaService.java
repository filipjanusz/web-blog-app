package filip.janusz.contactbook.service;

public interface RecaptchaService {
    boolean isResponseValid(String response);
}
