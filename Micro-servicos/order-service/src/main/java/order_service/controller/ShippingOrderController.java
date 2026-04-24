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

import order_service.entity.ShippingOrder;
import order_service.service.ShippingOrderService;

@RestController
@CrossOrigin(origins = "http://localhost:8084")
public class ShippingOrderController {

    @Autowired
    private ShippingOrderService shippingOrderService;

    @PostMapping("/shippingorder")
    public ResponseEntity<ShippingOrder> createShippingOrder(@RequestBody ShippingOrder shippingOrder) {
        ShippingOrder createdShippingOrder = shippingOrderService.createShippingOrder(shippingOrder);
        return new ResponseEntity<>(createdShippingOrder, HttpStatus.CREATED);
    }

    @GetMapping("/shippingorder")
    public ResponseEntity<List<ShippingOrder>> getAllShippingOrders() {
        List<ShippingOrder> shippingOrders = shippingOrderService.getAllShippingOrders();
        return new ResponseEntity<>(shippingOrders, HttpStatus.OK);
    }
}