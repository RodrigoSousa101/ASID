package order_service.service;

import java.util.List;

import order_service.entity.Orders;

public interface OrdersService {
    Orders createOrder(Orders order);
    List<Orders> getAllOrders();
}