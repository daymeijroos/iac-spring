package com.daymeijroos.iacspring.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(255)")
    private String id;

    @Column
    @NotEmpty(message = "E-mail address cannot be empty")
    @Email(message = "This is not a valid E-mail address")
    private String email;

    @Column
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @Column(updatable = false)
    private boolean admin = false;

    @Column
    @NotEmpty(message = "First name cannot be empty")
    private String first_name;

    @Column
    @NotEmpty(message = "Last name cannot be empty")
    private String last_name;

    @Column
    @NotEmpty(message = "Phone number cannot be empty")
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^[0-9]*$")
    private String phone;

    @Column
    @NotEmpty(message = "Country cannot be empty")
    private String country;

    @NotEmpty(message = "City cannot be empty")
    private String city;

    @NotEmpty(message = "Postal code cannot be empty")
    private String postal_code;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    //@Column(updatable = false)]
    //@ManyToMany
    //@JoinTable(
    //    name = "user_order",
    //    joinColumns = @JoinColumn(name = "user_id"),
    //    inverseJoinColumns = @JoinColumn(name = "order_id")
    //private List<Order> orders;

    @ManyToMany
    @JoinColumn(name = "cart_products")
    private List<Product> cart_products;

    // getters and setters for all fields
}
