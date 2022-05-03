package com.ing.devschool.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginResource {

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }
}
