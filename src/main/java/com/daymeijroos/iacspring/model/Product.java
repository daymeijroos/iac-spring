package com.daymeijroos.iacspring.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @NotBlank(message="Name cannot be empty.")
    private String name;

    @NotBlank(message="Description cannot be empty.")
    private String description;

    @NotNull(message="Price cannot be empty.")
    @Positive(message = "Price must be positive")
    private Float price;

    @NotBlank(message="ImageURL cannot be empty.")
    private String imageUrl;

    @ManyToOne
    @NotNull(message="Category cannot be empty")
    private Category category;
}
