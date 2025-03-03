package org.gslearn.eazyschool.service;

import lombok.extern.slf4j.Slf4j;
import org.gslearn.eazyschool.constants.EasySchoolConstants;
import org.gslearn.eazyschool.model.Contact;
import org.gslearn.eazyschool.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@ApplicationScope
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactService() {
        System.out.println("Contact service bean created");
    }

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(EasySchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if (Objects.nonNull(savedContact) && savedContact.getContactId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }


    public List<Contact> findMsgWithOpenStatus() {
        return contactRepository.findByStatus(EasySchoolConstants.OPEN);
    }

    public boolean updateMsgStatus(int contactId) {
        boolean isUpdated = false;
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        contactOptional.ifPresent(contact -> {
            contact.setStatus(EasySchoolConstants.CLOSE);
            contact.setUpdatedAt(LocalDateTime.now());
        });
        Contact contact = contactRepository.save(contactOptional.get());
        if (Objects.nonNull(contact) && contact.getUpdatedBy() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
