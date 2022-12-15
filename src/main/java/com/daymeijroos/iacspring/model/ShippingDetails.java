package com.daymeijroos.iacspring.model;

import com.daymeijroos.iacspring.enums.Country;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class ShippingDetails {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @OneToOne
    private User user;

    @NotBlank(message = "First name cannot be empty")
    private String first_name;

    @NotBlank(message = "Last name cannot be empty")
    private String last_name;

    @NotBlank(message = "Phone number cannot be empty")
    @Size(min = 10, max = 10, message = "Length does not match a regular phone number")
    @Pattern(regexp = "^[0-9]*$")
    @Column(columnDefinition = "VARCHAR(10)")
    private String phone;

    @NotBlank(message = "Country cannot be empty")
    @Enumerated
    private Country country;

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "Postal code cannot be empty")
    @Size(min = 4, max = 10, message = "Length does not match a postal code")
    @Column(columnDefinition = "VARCHAR(10)")
    private String postal_code;

    @NotBlank(message = "Address cannot be empty")
    private String address;
}
