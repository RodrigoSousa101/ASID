package order_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import order_service.entity.OrderDetails;
import order_service.repository.OrderDetailsRepository;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{
    
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;


    public OrderDetails createOrderDetails(OrderDetails orderDetails){

        return orderDetailsRepository.save(orderDetails);

    }
}
