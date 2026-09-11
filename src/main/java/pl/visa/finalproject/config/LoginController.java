package pl.visa.finalproject.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        System.out.println("Wchodze do kontorllera /login");
        return "login";
    }
}
