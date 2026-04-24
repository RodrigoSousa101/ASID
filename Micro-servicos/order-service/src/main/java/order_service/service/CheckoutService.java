package order_service.service;

import order_service.dto.CheckoutRequest;
import order_service.dto.CheckoutResponse;

public interface CheckoutService {
    CheckoutResponse createOrderFromUserCart(CheckoutRequest request);
}