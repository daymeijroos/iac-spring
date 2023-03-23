package com.daymeijroos.iacspring.Category;

import com.daymeijroos.iacspring.Product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @NotBlank(message="Category name cannot be empty.")
    private String name;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
