package filip.janusz.webblog.service;

public interface RecaptchaService {
    boolean isResponseValid(String response);
}
