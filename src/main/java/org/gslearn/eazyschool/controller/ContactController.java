package org.gslearn.eazyschool.controller;

import jakarta.validation.Valid;
import org.gslearn.eazyschool.model.Contact;
import org.gslearn.eazyschool.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ContactController {
    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact",new Contact());
        return "contact.html";
    }

//    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
//    public ModelAndView saveContactInformation(@RequestParam String name, @RequestParam String mobileNum, @RequestParam String email,
//                                               @RequestParam String subject, @RequestParam String message) {
//        log.info("Name: " + name);
//        log.info("Mobile Number: " + mobileNum);
//        log.info("Email: " + email);
//        log.info("Subject: " + subject);
//        log.info("Message: " + message);
//        return new ModelAndView("redirect:/contact");
//    }

    @RequestMapping(value="/saveMsg",method = POST)
    public String saveContactInformation(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
        if(errors.hasErrors()){
            log.info("Error in form"+errors);
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);
//        contactService.setCounter(contactService.getCounter()+1);
//        log.info("Counter Value is:"+ contactService.getCounter()
//        );
        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages")
    public ModelAndView displayMessage(Model model){
        List<Contact> contactList = contactService.findMsgWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsgs", contactList);
        return modelAndView;
    }


}
