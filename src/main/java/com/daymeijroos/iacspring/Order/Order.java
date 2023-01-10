package com.daymeijroos.iacspring.Order;

import com.daymeijroos.iacspring.Product.Product;
import com.daymeijroos.iacspring.ShippingDetails.ShippingDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.sound.sampled.Line;
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

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<LineItem> lineItems = new ArrayList<>();

    @Enumerated
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @ManyToOne
    @JoinColumn(name = "shipping_details_id")
    private ShippingDetails shippingDetails;

    @Enumerated
    private ShippingStatus shippingStatus = ShippingStatus.UNSHIPPED;

    public Float getTotalPrice() {
        float totalPrice = 0;
        for (LineItem lineItem : lineItems) {
            totalPrice += lineItem.product.getPrice();
        }
        return totalPrice;
    }
}