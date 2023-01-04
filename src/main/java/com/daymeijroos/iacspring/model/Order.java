package com.daymeijroos.iacspring.model;

import com.daymeijroos.iacspring.enums.PaymentStatus;
import com.daymeijroos.iacspring.enums.ShippingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @NotNull(message="User cannot be null.")
    private String userId;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Date dateCreated = Date.valueOf(LocalDate.now());

    private Date dateModified = Date.valueOf(LocalDate.now());

    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    @Enumerated
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @ManyToOne
    @JoinColumn(name = "shipping_details_id")
    private ShippingDetails shippingDetails;

    @Enumerated
    private ShippingStatus shippingStatus = ShippingStatus.UNSHIPPED;

    public Float getTotalPrice() {
        float totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}