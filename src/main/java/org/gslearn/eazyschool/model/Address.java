package org.gslearn.eazyschool.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @NotBlank(message = "Address1 must not be blank")
    @Size(min = 5, message = "Address1 must be atleast 5 character long")
    private String address1;

    private String address2;

    @NotBlank(message = "City must not be blank")
    @Size(min=5,message = "City must be atleast 5 character long")
    private String city;

    @NotBlank(message = "State must not be blank")
    @Size(min=5,message = "State must be atleast 5 character long")
    private String state;

    @NotBlank(message = "Zip must not be blank")
    @Pattern(regexp = "(^$|[0-9]{5})", message = "Zip code must be 5 digit")
    private String zipCode;
}
