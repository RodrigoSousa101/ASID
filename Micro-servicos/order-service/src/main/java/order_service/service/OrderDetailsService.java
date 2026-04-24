package order_service.service;

import org.springframework.stereotype.Service;

import order_service.entity.OrderDetails;

@Service
public interface OrderDetailsService {
    OrderDetails createOrderDetails(OrderDetails orderDetails);
}
