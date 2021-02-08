package org.example.configiki;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "defaultController")
public class IndexController{

    @RequestMapping("/")
    public String index() {
        return "configdeneme";
    }
}
