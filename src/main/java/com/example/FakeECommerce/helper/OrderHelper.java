package com.example.FakeECommerce.helper;

import com.example.FakeECommerce.entity.Order;
import com.example.FakeECommerce.entity.OrderProducts;
import com.example.FakeECommerce.entity.OrderStatus;
import com.example.FakeECommerce.entity.Product;
import com.example.FakeECommerce.exception.ExceptionHelper;
import com.example.FakeECommerce.mapper.OrderMapper;
import com.example.FakeECommerce.repository.OrderProductsRepository;
import com.example.FakeECommerce.repository.OrderRepository;
import com.example.FakeECommerce.repository.ProductRepository;
import com.example.FakeECommerce.request.CreateOrderRequestDto;
import com.example.FakeECommerce.request.OrderItemActionDto;
import com.example.FakeECommerce.request.OrderItemRequestDto;
import com.example.FakeECommerce.request.UpdateOrderRequestDto;

import com.example.FakeECommerce.response.OrderResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderHelper {

    private final OrderRepository orderRepository;
    private final ExceptionHelper exceptionHelper;
    private final ProductRepository productRepository;
    private final OrderProductsRepository orderProductsRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderResponseDto createOrder(CreateOrderRequestDto createOrderRequestDto) {
        log.info("Inside createOrder method of OrderHelper");
        Order order = Order.builder().orderStatus(OrderStatus.PENDING).build();
        order = orderRepository.save(order);

        List<OrderProducts> orderProducts = Collections.emptyList();
        if(!CollectionUtils.isEmpty(createOrderRequestDto.getOrderItemRequestDtoList())) {
            List<Long> productIds =createOrderRequestDto.getOrderItemRequestDtoList().stream()
                    .map(OrderItemRequestDto::getProductId)
                    .toList();

            List<Product> products = productRepository.findAllById(productIds);
            Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
            for (Long id : productIds) {
                if(!productMap.containsKey(id)) {
                    log.error("Product with id {} not found", id);
//                    throw new BadRequestException("Product with id " + id + " not found");
                }
            }
            orderProducts = new ArrayList<>();

            for(var itemDto : createOrderRequestDto.getOrderItemRequestDtoList()) {
                Product product = productMap.get(itemDto.getProductId());
                orderProducts.add(OrderProducts.builder()
                                .order(order)
                                .product(product)
                                .quantity(itemDto.getQuantity() != null? itemDto.getQuantity() : 1)
                        .build());
            }
            orderProducts = orderProductsRepository.saveAll(orderProducts);
        }
        return orderMapper.mapToOrderResponseDto(order, orderProducts);
    }

    public List<OrderResponseDto> getAllOrders() {
        log.info("Inside getAllOrders method of OrderHelper");
        List<Order> orders = orderRepository.findAll();
        if (CollectionUtils.isEmpty(orders)) {
            return Collections.emptyList();
        }
        return orders.stream()
                .map(order -> {
                    List<OrderProducts> orderProducts = orderProductsRepository.findByOrderId(order.getId());
                    return orderMapper.mapToOrderResponseDto(order, orderProducts);
                })
                .toList();
    }

    public OrderResponseDto getOrderById(Long orderId) {
        log.info("Inside getOrderById method of OrderHelper");
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            List<OrderProducts> orderProducts = orderProductsRepository.findByOrderId(order.get().getId());
            return orderMapper.mapToOrderResponseDto(order.get(), orderProducts);
        }
        return null;
    }

    public void deleteOrder(Long orderId) {
        log.info("Inside deleteOrder method of OrderHelper");
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with id " + orderId + " not found"));
        orderRepository.delete(order);
    }

    public OrderResponseDto updateOrder(Long orderId, UpdateOrderRequestDto updateOrderRequestDto) {
        log.info("Inside updateOrder method of OrderHelper");
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with id " + orderId + " not found"));
        order.setOrderStatus(updateOrderRequestDto.getOrderStatus());
         orderRepository.save(order);
        List<OrderProducts> updatedOrderProducts = new ArrayList<>();

         if(updateOrderRequestDto.getOrderItems() != null) {
             List<Long> productIds = updateOrderRequestDto.getOrderItems().stream().map(itemDto -> itemDto.getProductId()).toList();
             List<Product> products = productRepository.findAllById(productIds);

             Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));

             for(var pid : productIds) {
                 if(!productMap.containsKey(pid)) {
                     throw new RuntimeException("Product not found with id: " + pid);
                 }
             }

             List<OrderProducts> toSave = new ArrayList<>();
             List<OrderProducts> toDelete = new ArrayList<>();


             Map<Long, OrderProducts> existingItems = orderProductsRepository.findByOrderWithProduct(order)
                     .stream().collect(Collectors.toMap(op -> op.getProduct().getId(), Function.identity()));

             for(OrderItemActionDto orderItemActionDto : updateOrderRequestDto.getOrderItems()) {
                 Product product = productMap.get(orderItemActionDto.getProductId());
                 OrderProducts existing = existingItems.get(product.getId());

                 switch (orderItemActionDto.getAction()) {
                     case ADD -> {
                         if(existing != null) {
                             int addQty = orderItemActionDto.getQuantity() != null ? orderItemActionDto.getQuantity() : 1;
                             existing.setQuantity(existing.getQuantity() + addQty);
                             toSave.add(existing);
                         }else {
                             OrderProducts newItem = OrderProducts
                                     .builder()
                                     .order(order)
                                     .product(product)
                                     .quantity(orderItemActionDto.getQuantity() != null ? orderItemActionDto.getQuantity() : 1)
                                     .build();
                             existingItems.put(product.getId(), newItem);
                             toSave.add(newItem);
                         }
                     }
                     case REMOVE -> {
                         if(existing == null) {
                             throw new RuntimeException("Product not found with id: " + product.getId());
                         }
                         toDelete.add(existing);
                         existingItems.remove(product.getId());
                     }
                     case INCREMENT -> {
                         if(existing == null) {
                             throw new RuntimeException("Product not found with id: " + product.getId());
                         }
                         existing.setQuantity(existing.getQuantity() + 1);
                         toSave.add(existing);

                     }
                     case DECREMENT -> {
                         if(existing == null) {
                             throw new RuntimeException("Product not found with id: " + product.getId());
                         }
                         if(existing.getQuantity() <= 1) {
                             toDelete.add(existing);
                             existingItems.remove(product.getId());
                         } else {
                             existing.setQuantity(existing.getQuantity() - 1);
                             toSave.add(existing);
                         }


                     }

                 }
                 updatedOrderProducts.addAll(toSave);
                 updatedOrderProducts.addAll(toDelete);
                 if(!toSave.isEmpty()) {
                     orderProductsRepository.saveAll(toSave);
                 }
                 if(!toDelete.isEmpty()) {
                     orderProductsRepository.deleteAll(toDelete);
                 }
             }
         }
        return orderMapper.mapToOrderResponseDto(order, updatedOrderProducts);
    }


}
