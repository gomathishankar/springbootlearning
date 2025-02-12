package org.gslearn.eazyschool.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Contact {
    @NotBlank(message = "Name should not be blank")
    @Size(min = 3)
    String name;
    @NotBlank(message = "Phone number should not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digit long")
    String mobileNum;
    @NotBlank(message = "Email cannot be null")
    String email;
    @NotBlank(message = "Subject message cannot be blank")
    @Size(min = 5, message = "Subject must be at least 5 characters long")
    String subject;
    @NotBlank(message = "Message cannot be not blank")
    @Size(min = 10, message = "min 10 character should be there for message")
    String message;

}
