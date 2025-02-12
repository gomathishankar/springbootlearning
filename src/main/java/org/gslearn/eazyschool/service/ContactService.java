package org.gslearn.eazyschool.service;

import lombok.extern.slf4j.Slf4j;
import org.gslearn.eazyschool.model.Contact;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@Service
//@RequestScope
//@SessionScope
@ApplicationScope
public class ContactService {

    private int counter = 0;

    public ContactService(){
        System.out.println("Contact service bean created");
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        log.info(contact.toString());

    }
}
