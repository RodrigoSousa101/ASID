package order_service.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class Orders {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private Long id;

    @Column
    private Date orderDate;

    @Column
    private double totalPrice;

    @Column(nullable = false)
    private Long userId;

    // @ManyToOne
    // @JoinColumn(name = "shippingorder_id")
    // private ShippingOrder shippingOrder;
}
