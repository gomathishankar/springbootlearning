package org.gslearn.eazyschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="contact_msg")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SqlResultSetMapping.count", columns = @ColumnResult(name = "cnt"))
})
@NamedQueries({
        @NamedQuery(name = "Contact.findOpenMsgs",
                query = "SELECT c FROM Contact c WHERE c.status = :status"),
        @NamedQuery(name = "Contact.updateMsgStatus",
                query = "UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Contact.findOpenMsgsNative",
                query = "SELECT * FROM contact_msg c WHERE c.status = :status"
                ,resultClass = Contact.class),
        @NamedNativeQuery(name = "Contact.findOpenMsgsNative.count",
                query = "select count(*) as cnt from contact_msg c where c.status = :status",
                resultSetMapping = "SqlResultSetMapping.count"),
        /*Spring Data JPA doesn’t support dynamic sorting for native queries.
        Doing that would require Spring Data to analyze the provided statement and generate
        the ORDER BY clause in the database-specific dialect. This would be a very complex operation
        and is currently not supported by Spring Data JPA.*/
        @NamedNativeQuery(name = "Contact.updateMsgStatusNative",
                query = "UPDATE contact_msg c SET c.status = ?1 WHERE c.contact_id = ?2")
})
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
