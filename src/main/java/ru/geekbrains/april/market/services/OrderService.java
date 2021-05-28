package ru.geekbrains.april.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.april.market.dtos.ProductDto;
import ru.geekbrains.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.april.market.models.*;
import ru.geekbrains.april.market.repositories.OrderRepository;
import ru.geekbrains.april.market.repositories.ProductRepository;
import ru.geekbrains.april.market.utils.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public Order createOrderForCurrentUser(User user, String phone, String address) {
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getSum());
        // todo распутать этот кусок
        order.setItems(cart.getItems());
        for (OrderItem oi : cart.getItems()) {
            oi.setOrder(order);
        }
        order.setPhone(phone);
        order.setAddress(address);
        order = orderRepository.save(order);
        cart.clear();
        return order;
    }
}
