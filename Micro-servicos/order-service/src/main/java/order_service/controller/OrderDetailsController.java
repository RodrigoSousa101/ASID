package order_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import order_service.entity.OrderDetails;
import order_service.service.OrderDetailsService;


@RestController
@CrossOrigin(origins = "http://localhost:8084")
public class OrderDetailsController {
    
    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping("/orderdetails")
    public ResponseEntity<OrderDetails> createOrderDetails(@RequestBody OrderDetails orderDetails){

        OrderDetails orderedDetails = orderDetailsService.createOrderDetails(orderDetails);

        return new ResponseEntity<>(orderedDetails,HttpStatus.CREATED);

    }
}
