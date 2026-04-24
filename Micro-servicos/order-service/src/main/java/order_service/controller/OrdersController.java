package order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import order_service.entity.Orders;
import order_service.dto.CheckoutRequest;
import order_service.dto.CheckoutResponse;
import order_service.service.CheckoutService;
import order_service.service.OrdersService;

@RestController
@CrossOrigin(origins = "http://localhost:8084")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/orders/checkout")
    public ResponseEntity<CheckoutResponse> checkout(@RequestBody CheckoutRequest request) {
        CheckoutResponse checkoutResponse = checkoutService.createOrderFromUserCart(request);
        return new ResponseEntity<>(checkoutResponse, HttpStatus.OK);
    }
}