package cart_service.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartid")
    private Long id;

    @Column(nullable = false)
    private LocalDate createdDate;

    @Column(nullable = false)
    private Long userId;
}
