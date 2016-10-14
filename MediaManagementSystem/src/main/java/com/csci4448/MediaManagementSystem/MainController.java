package com.csci4448.MediaManagementSystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/hello")
    public String test() {
        System.out.println("In controller");
        return "hello";
    }

}
