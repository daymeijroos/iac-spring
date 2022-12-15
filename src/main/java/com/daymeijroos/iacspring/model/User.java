package com.daymeijroos.iacspring.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @NotBlank(message = "E-mail address cannot be empty")
    @Email(message = "This is not a valid E-mail address")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @ToString.Exclude
    private String password;

    private boolean admin = false;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private ShippingDetails shippingDetails;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();
}
