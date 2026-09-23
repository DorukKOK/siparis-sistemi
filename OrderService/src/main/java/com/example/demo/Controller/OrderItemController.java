package com.example.demo.Controller;

import com.example.demo.Model.OrderItemEntity;
import com.example.demo.Service.OrderItemService;
import com.example.demo.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService, OrderService orderService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public List<OrderItemEntity> getAllItems(){
        return orderItemService.getAllItems();
    }

    @GetMapping("/{id}")
    public OrderItemEntity getItemById(@PathVariable Long id){
        return orderItemService.getItemById(id);
    }

    @PostMapping("/{orderId}")
    public OrderItemEntity createItem(@RequestBody OrderItemEntity orderItem, @PathVariable Long orderId){
        return orderItemService.createOrderItem(orderItem, orderId);
    }

    @PutMapping("/{id}")
    public OrderItemEntity updateItem(@PathVariable Long id, @RequestBody OrderItemEntity orderItem){
        return orderItemService.updateOrderItem(id, orderItem);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id){
        orderItemService.deleteOrderItem(id);
    }
}