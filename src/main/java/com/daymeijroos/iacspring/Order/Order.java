package com.daymeijroos.iacspring.Order;

import com.daymeijroos.iacspring.ShippingDetails.ShippingDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;
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

    @Setter(AccessLevel.NONE)
    private Date dateCreated;

    @Setter(AccessLevel.NONE)
    private Date dateModified;

    @OneToMany
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
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