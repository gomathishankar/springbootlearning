package org.gslearn.eazyschool.rest;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.gslearn.eazyschool.constants.EasySchoolConstants;
import org.gslearn.eazyschool.model.Contact;
import org.gslearn.eazyschool.model.Response;
import org.gslearn.eazyschool.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/api/contact", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
@CrossOrigin(origins = "*")
public class ContactRestController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/getMessageByStatus")
    public List<Contact> getMesageByStatus(@RequestParam("status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMsgByStatus")
    public List<Contact> getAllMsgByStatus(@RequestBody Contact contact) {
        if (null != contact && null != contact.getStatus()) {
            return contactRepository.findByStatus(contact.getStatus());
        }
        return List.of();
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(@RequestHeader("invocationFrom") String invocationFrom, @Valid @RequestBody Contact contact) {
        log.info(String.format("Header invocationFrom: %s", invocationFrom));
        contactRepository.save(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message saved Successfully");
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("isMsgSaved", "true")
                .body(response);
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((k, v) -> {
            log.info(String.format("Header key: %s, value: %s", k, v));
        });
        Contact contact = requestEntity.getBody();
        contactRepository.deleteById(contact.getContactId());
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message deleted Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq) {
        Response response = new Response();
        Optional<Contact> contact = contactRepository.findById(contactReq.getContactId());
        if(contact.isPresent()) {
            contact.get().setStatus(EasySchoolConstants.CLOSE);
            contactRepository.save(contact.get());
        } else{
            response.setStatusCode("400");
            response.setStatusMsg("Contact not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setStatusCode("200");
        response.setStatusMsg("Message closed Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
