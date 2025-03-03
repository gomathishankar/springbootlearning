package org.gslearn.eazyschool.security;

import org.gslearn.eazyschool.model.Person;
import org.gslearn.eazyschool.model.Roles;
import org.gslearn.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Component
public class EazyschoolUsernamePwdAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Person person = personRepository.findByEmail(email);
        if (Objects.nonNull(person) && person.getPersonId() > 0 &&
                passwordEncoder.matches(password,person.getPwd())) {
            return new UsernamePasswordAuthenticationToken(email, null, getGrantedAuthories(person.getRoles()));
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    private List<GrantedAuthority> getGrantedAuthories(Roles roles) {
        return List.of(new SimpleGrantedAuthority("ROLE_"+roles.getRoleName()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
