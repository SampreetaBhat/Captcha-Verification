package com.captcha.reCaptchaGoogle.Controller;

import com.captcha.reCaptchaGoogle.Service.ReCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/V1/captcha")
public class ReCaptchaController {
        @Autowired
        private ReCaptchaService reCaptchaService ;

        @GetMapping
        public String showCaptchaPage() {
            return "reCaptcha";
        }

        @PostMapping
        public String verifyCaptcha(@RequestParam("g-recaptcha-response") String recaptchaResponse, Model model) {
            boolean isHuman = reCaptchaService.verifyRecaptcha(recaptchaResponse);
            if (isHuman) {
                model.addAttribute("message", " Verified as Human!");
            } else {
                model.addAttribute("message", " Failed CAPTCHA! You might be a bot.");
            }
            return "reCaptcha";
        }
    }


