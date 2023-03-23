package com.daymeijroos.iacspring.ShippingDetails;

import com.daymeijroos.iacspring.Order.Order;
import com.daymeijroos.iacspring.ShippingDetails.Country;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shipping_details")
public class ShippingDetails {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    private String userId;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @NotBlank(message = "E-mail address cannot be empty")
    @Email(message = "This is not a valid E-mail address")
    @Column(unique = true)
    private String email;

    @Nullable
    @Size(min = 10, max = 10, message = "Length does not match a regular phone number")
    @Pattern(regexp = "^[0-9]*$")
    @Column(columnDefinition = "VARCHAR(10)")
    private String phone = null;

    @NotNull(message = "Country cannot be null")
    @Enumerated(EnumType.STRING)
    private Country country;

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "Postal code cannot be empty")
    @Size(min = 4, max = 10, message = "Length does not match a postal code")
    @Column(columnDefinition = "VARCHAR(10)")
    private String postalCode;

    @NotBlank(message = "Address cannot be empty")
    private String address;
}
