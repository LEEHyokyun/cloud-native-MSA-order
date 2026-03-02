package com.msa.user.service;

import com.msa.user.model.entity.Order;
import com.msa.user.model.request.OrderCreateRequest;
import com.msa.user.model.response.OrderResponse;
import com.msa.user.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponse create(Long userId, OrderCreateRequest orderCreateRequest) {
        Order order = Order.create(
                userId,
                orderCreateRequest.getProductId(),
                orderCreateRequest.getOrderQty(),
                orderCreateRequest.getOrderUnitPrice()
        );

        orderRepository.save(order);

        return OrderResponse.from(order);
    }

    public List<OrderResponse> readOrderOfUser(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(OrderResponse::from)
                .toList();
    }

    public List<OrderResponse> readAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderResponse::from)//Entity > Dto
                .toList();
    }

}
