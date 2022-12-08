package com.daymeijroos.iacspring.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
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
    @NotNull(message="Name cannot be empty.")
    private String name;

    @Column
    @NotNull(message="Description cannot be empty.")
    private String description;

    @Column
    @NotNull(message="Price cannot be empty.")
    private Float price;

    @Column
    @NotNull(message="Image cannot be empty.")
    private String imageURL;
}
