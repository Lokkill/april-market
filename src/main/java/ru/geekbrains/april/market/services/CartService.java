package ru.geekbrains.april.market.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ru.geekbrains.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.april.market.models.OrderItem;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.services.ProductService;
import ru.geekbrains.april.market.utils.Cart;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService implements Serializable {
    private final ProductService productService;

    public void addToCart(Cart cart, Long id) {
        for (OrderItem orderItem : cart.getItems()) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.incrementQuantity();
                recalculate(cart);
                return;
            }
        }

        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + id + " (add to cart)"));
        cart.getItems().add(new OrderItem(product));
        recalculate(cart);
    }

    public void clear(Cart cart) {
        cart.getItems().clear();
        recalculate(cart);
    }

    private void recalculate(Cart cart) {
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderItem oi : cart.getItems()) {
            sum = sum.add(oi.getPrice());
        }
        cart.setSum(sum);
    }

    public List<OrderItem> getItems(Cart cart) {
        return Collections.unmodifiableList(cart.getItems());
    }
}
