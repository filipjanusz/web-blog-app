package filip.janusz.webblog.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecaptchaServiceImpl implements RecaptchaService {

    private static final class RecaptchaResponse {
        @JsonProperty("success")
        private boolean success;
        @JsonProperty("error-codes")
        private List<String> errorCodes;
    }

    @Value("${recaptcha.url}")
    private String recaptchaUrl;

    @Value("${recaptcha.secret-key}")
    private String recaptchaSecretKey;

    private RestTemplate restTemplate;

    @Autowired
    public RecaptchaServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean isResponseValid(String response) {
        RecaptchaResponse recaptchaResponse = restTemplate.postForEntity(
                recaptchaUrl,
                createBody(recaptchaSecretKey, response),
                RecaptchaResponse.class).getBody();

        if(recaptchaResponse.success) {
            return true;
        } else {
            return false;
        }
    }

    private MultiValueMap<String, String> createBody(String recaptchaSecretKey, String response) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret", recaptchaSecretKey);
        map.add("response", response);
        return map;
    }
}
