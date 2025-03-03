package org.gslearn.eazyschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    @NotBlank(message = "Name should not be blank")
    @Size(min = 3)
    private String name;
    @NotBlank(message = "Phone number should not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digit long")
    private String mobileNum;
    @NotBlank(message = "Email cannot be null")
    private String email;
    @NotBlank(message = "Subject message cannot be blank")
    @Size(min = 5, message = "Subject must be at least 5 characters long")
    private String subject;
    @NotBlank(message = "Message cannot be not blank")
    @Size(min = 10, message = "min 10 character should be there for message")
    private String message;

    private String status;

}
