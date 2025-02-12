package com.captcha.reCaptchaGoogle.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@Service

public class ReCaptchaService {

        private static final String SECRET_KEY = "6LcCl9QqAAAAACSpixsMo8XVdupUpLkSa46svzDv";
        private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

        public boolean verifyRecaptcha(String recaptchaResponse) {
            RestTemplate restTemplate = new RestTemplate();
            String url = VERIFY_URL + "?secret=" + SECRET_KEY + "&response=" + recaptchaResponse;

            ResponseEntity<Map> response = restTemplate.postForEntity(url, null, Map.class);
            Map<String, Object> body = response.getBody();

            if (body == null || !body.containsKey("success")) {
                return false;
            }

            boolean success = (Boolean) body.get("success");


            if (body.containsKey("score")) {
                double score = (Double) body.get("score");
                return success && score >= 0.5;
            }

            return success;
        }
    }


