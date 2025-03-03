package org.gslearn.eazyschool.service;

import org.gslearn.eazyschool.constants.EasySchoolConstants;
import org.gslearn.eazyschool.model.Person;
import org.gslearn.eazyschool.model.Roles;
import org.gslearn.eazyschool.repository.PersonRepository;
import org.gslearn.eazyschool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolesRepository rolesRepository;

    public boolean createNewPerson(Person person) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(EasySchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if(Objects.nonNull(person) && person.getPersonId()>0) {
            isSaved = true;
        }
        return isSaved;
    }
}
