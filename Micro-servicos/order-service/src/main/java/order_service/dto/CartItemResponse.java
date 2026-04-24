package order_service.dto;

import lombok.Data;

@Data
public class CartItemResponse {
    private Long id;
    private Long cartId;
    private Long bookId;
    private Integer quantity;
    private Double unitPrice;
    private Double subTotal;
}