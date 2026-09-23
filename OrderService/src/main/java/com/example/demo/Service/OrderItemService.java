package com.example.demo.Service;

import com.example.demo.Model.OrderEntity;
import com.example.demo.Model.OrderItemEntity;
import com.example.demo.Model.ProductModelDTO;
import com.example.demo.Repository.OrderItemRepository;
import com.example.demo.Repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final RestClient restClient;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderRepository orderRepository,RestClient restClient) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.restClient = restClient;
    }

    public OrderItemEntity createOrderItem(OrderItemEntity orderItem, Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Böyle bir sipariş yok."));
        ProductModelDTO product = restClient.get()
                    .uri("http://product-service:8082/products/" + orderItem.getProductId())
                                .retrieve()
                                        .body(ProductModelDTO.class);
        orderItem.setUnitPrice(product.getPrice());
        orderItem.setOrder(order);

        return orderItemRepository.save(orderItem);
    }
    public List<OrderItemEntity> getAllItems(){
        return orderItemRepository.findAll();
    }
    public OrderItemEntity getItemById(Long id){
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Böyle bir sipariş kalemi yok"));
    }
    public void deleteOrderItem(Long id){
        if (!orderItemRepository.existsById(id)){
            throw new RuntimeException("Böyle bir sipariş kalemi yok");
        }
        orderItemRepository.deleteById(id);
    }
    public OrderItemEntity updateOrderItem(Long id,OrderItemEntity updatedData){
        OrderItemEntity existingItem = orderItemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Böyle bir sipariş kalemi yok"));
        existingItem.setProductId(updatedData.getProductId());
        existingItem.setQuantity(updatedData.getQuantity());
        existingItem.setUnitPrice(updatedData.getUnitPrice());

        return orderItemRepository.save(existingItem);
    }
}
