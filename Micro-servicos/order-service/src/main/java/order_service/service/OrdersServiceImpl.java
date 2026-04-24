package order_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import order_service.entity.Orders;
import order_service.repository.OrdersRepository;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }
}