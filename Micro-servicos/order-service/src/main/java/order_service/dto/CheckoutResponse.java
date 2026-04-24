package order_service.dto;

import java.util.List;

import lombok.Data;

@Data
public class CheckoutResponse {
    private Long userId;
    private Long cartId;
    private List<CartItemResponse> cartItems;
    private Double cartTotal;
    private Double amountPaid;
    private Boolean paymentSuccessful;
    private String paymentMessage;
    private Double changeAmount;
    private Long orderId;
}