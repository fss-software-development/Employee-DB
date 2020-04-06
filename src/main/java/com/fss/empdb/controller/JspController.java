package com.fss.empdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {

    @RequestMapping("/")
    public String defaultPage() {
        return "index";
    }

    @RequestMapping("/home")
    public String defaultPageHome() {
        return "home";
    }

}
