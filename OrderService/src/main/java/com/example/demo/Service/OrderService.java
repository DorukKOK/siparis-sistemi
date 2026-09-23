package com.example.demo.Service;

import com.example.demo.Model.OrderEntity;
import com.example.demo.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderEntity createOrder(OrderEntity order){
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }

    public List<OrderEntity> getAllOrders(){
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Böyle bir sipariş yok"));

    }
    public OrderEntity updateOrder(Long id,OrderEntity updatedData){
        OrderEntity existingOrder = orderRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Böyle bir sipariş yok"));
        existingOrder.getStatus();
        return orderRepository.save(existingOrder);
    }
    public void deleteOrder(Long id){
        if (!orderRepository.existsById(id)){
            throw new RuntimeException("Böyle bir sipariş yok");
        }
        orderRepository.deleteById(id);
    }
}