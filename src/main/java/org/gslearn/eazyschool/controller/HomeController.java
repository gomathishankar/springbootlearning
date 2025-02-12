package org.gslearn.eazyschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"/","","home",})
    public String homePage(Model model){
        model.addAttribute("username","Saranya Gomathi");
     return "home.html";
    }
}
