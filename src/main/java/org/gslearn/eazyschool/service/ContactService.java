package org.gslearn.eazyschool.service;

import lombok.extern.slf4j.Slf4j;
import org.gslearn.eazyschool.constants.EasySchoolConstants;
import org.gslearn.eazyschool.model.Contact;
import org.gslearn.eazyschool.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
//@RequestScope
//@SessionScope
@ApplicationScope
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

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
        boolean isSaved = false;
        contact.setStatus(EasySchoolConstants.OPEN);
        contact.setCreatedAt(LocalDateTime.now());
        contact.setCreatedBy(EasySchoolConstants.ANONYMOUS);
        int result = contactRepository.saveMessage(contact);
        if(result > 1) {
            isSaved = true;
        }
        log.info(contact.toString());
    }


    public List<Contact> findMsgWithOpenStatus() {
        return contactRepository.findMsgWithStatus(EasySchoolConstants.OPEN);
    }
}
