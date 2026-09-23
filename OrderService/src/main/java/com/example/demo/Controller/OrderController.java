package com.example.demo.Controller;

import com.example.demo.Model.OrderEntity;
import com.example.demo.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public List<OrderEntity> getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/{id}")
    public OrderEntity getOrder(@PathVariable Long id){
        return orderService.getOrderById(id);
    }
    @PostMapping
    public OrderEntity createOrder(@RequestBody OrderEntity order){
        return orderService.createOrder(order);
    }
    @PutMapping("/{id}")
    public OrderEntity updateOrder(@PathVariable Long id,@RequestBody OrderEntity order){
        return orderService.updateOrder(id, order);
    }
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }
}
