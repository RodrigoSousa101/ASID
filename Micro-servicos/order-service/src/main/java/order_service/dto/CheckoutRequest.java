package order_service.dto;

import lombok.Data;

@Data
public class CheckoutRequest {
    private Long userId;
    private Double amountPaid;
}