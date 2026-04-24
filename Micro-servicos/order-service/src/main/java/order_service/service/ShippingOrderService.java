package order_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import order_service.entity.ShippingOrder;



@Service
public interface ShippingOrderService {
    
    ShippingOrder createShippingOrder(ShippingOrder shippingOrder);

    List<ShippingOrder> getAllShippingOrders();
}
