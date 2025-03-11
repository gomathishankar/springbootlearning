package org.gslearn.eazyschool.service;

import lombok.extern.slf4j.Slf4j;
import org.gslearn.eazyschool.config.EazySchoolProps;
import org.gslearn.eazyschool.constants.EasySchoolConstants;
import org.gslearn.eazyschool.model.Contact;
import org.gslearn.eazyschool.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    EazySchoolProps eazySchoolProps;

    /**
     * Save Contact Details into DB
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(EasySchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if(null != savedContact && savedContact.getContactId()>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){

        Pageable pageable = PageRequest.of(pageNum - 1, eazySchoolProps.getPageSize(),
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatusWithQuery(
                EasySchoolConstants.OPEN,pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        int rows = contactRepository.updateMsgStatusNative(EasySchoolConstants.CLOSE,contactId);
        if(rows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
