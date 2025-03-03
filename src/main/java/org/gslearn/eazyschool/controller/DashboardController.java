package org.gslearn.eazyschool.controller;

import jakarta.servlet.http.HttpSession;
import org.gslearn.eazyschool.model.Person;
import org.gslearn.eazyschool.repository.PersonRepository;
import org.gslearn.eazyschool.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayedDashBoard(Model model, Authentication auth, HttpSession session) {
        Person person = (Person) personRepository.findByEmail(auth.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", auth.getAuthorities().toString());
//        throw new RuntimeException("Error Login");
        session.setAttribute("loggedInPerson", person);
        return "dashboard.html";
    }
}
