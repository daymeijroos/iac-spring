package com.daymeijroos.iacspring.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(255)")
    private String id;

    @Column
    @NotEmpty(message="Name cannot be empty.")
    private String name;

    @Column
    @NotEmpty(message="Description cannot be empty.")
    private String description;

    @Column
    @NotEmpty(message="Price cannot be empty.")
    @Positive(message = "Price must be positive")
    private Float price;

    @Column
    @NotEmpty(message="ImageURL cannot be empty.")
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotEmpty(message="Category cannot be empty")
    private Category category;
}
