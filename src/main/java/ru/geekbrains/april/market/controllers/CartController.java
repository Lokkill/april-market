package ru.geekbrains.april.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.dtos.CartDto;
import ru.geekbrains.april.market.services.CartService;
import ru.geekbrains.april.market.utils.Cart;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final Cart cart;
    private final CartService cartService;

    @GetMapping("/add/{productId}")
    public void addToCart(@PathVariable(name = "productId") Long id) {
//        if (session.getAttribute("cart") == null){
//            session.setAttribute("cart", cart);
//        }
//        Cart sessionCart = (Cart) session.getAttribute("cart");
//        sessionCart.addToCart(id);
//        session.setAttribute("cart", sessionCart);
        cartService.addToCart(cart, id);
    }

    @GetMapping("/clear")
    public void clearCart() {
//        if (session.getAttribute("cart") == null){
//            session.setAttribute("cart", cart);
//        }
//        Cart sessionCart = (Cart) session.getAttribute("cart");
//        sessionCart.clear();
//        session.setAttribute("cart", sessionCart);
        cartService.clear(cart);
    }

    @GetMapping
    public CartDto getCart() {
//        if (session.getAttribute("cart") == null){
//            session.setAttribute("cart", cart);
//        }
//        return new CartDto((Cart) session.getAttribute("cart"));
        return new CartDto(cart);
    }
}
