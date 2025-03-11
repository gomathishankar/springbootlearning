package org.gslearn.eazyschool.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.gslearn.eazyschool.model.Person;
import org.gslearn.eazyschool.repository.PersonRepository;
import org.gslearn.eazyschool.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @Value("${eazyschool.pageSize}")
    private int pageSize;

    @Value("${eazyschool.contact.successMsg}")
    private String successMsg;

    @Autowired
    Environment environment;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayedDashBoard(Model model, Authentication auth, HttpSession session) {
        Person person = (Person) personRepository.findByEmail(auth.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", auth.getAuthorities().toString());
        if(null != person.getEazyClass() && null != person.getEazyClass().getName()) {
            model.addAttribute("enrolledClass", person.getEazyClass().getName());
        }
        session.setAttribute("loggedInPerson", person);
        logMessage();
        return "dashboard.html";
    }

    private void logMessage() {
        log.error("default page size is: "+pageSize);
        log.error(successMsg);
        log.error(environment.getProperty("JAVA_HOME"));
    }
}
