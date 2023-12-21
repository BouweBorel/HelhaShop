package com.helha.shop.service;

import com.helha.shop.model.Cloth;
import com.helha.shop.model.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartService = CartService.getINSTANCE();
    }

    @Test
    void addToCart() {
        Cloth cloth1 = new Cloth("MAGA", 29, 3, Size.M);
        Cloth cloth2 = new Cloth("MAGP", 30, 1, Size.XL);

        List<Cloth> cart = cartService.addToCart(cloth1);

        assertTrue(cart.contains(cloth1));
        assertEquals(1, cart.size());
        assertEquals(29, cart.get(0).getPrice());
        assertEquals(3, cart.get(0).getQuantity());
        assertEquals(Size.M, cart.get(0).getSize());

        // Add another cloth
        cart = cartService.addToCart(cloth2);

        assertTrue(cart.contains(cloth2));
        assertEquals(2, cart.size());
        assertEquals(30, cart.get(1).getPrice());
        assertEquals(1, cart.get(1).getQuantity());
        assertEquals(Size.XL, cart.get(1).getSize());

        // Add more quantity of an existing cloth
        Cloth cloth1Updated = new Cloth("MAGA", 29, 2, Size.M);
        cart = cartService.addToCart(cloth1Updated);

        assertEquals(2, cart.size());
        assertEquals(5, cart.get(0).getQuantity());
    }

    @Test
    void removeFromCart() {
        Cloth cloth1 = new Cloth("MAGA", 29, 3, Size.M);
        Cloth cloth2 = new Cloth("MAGP", 30, 1, Size.XL);

        List<Cloth> cart = cartService.addToCart(cloth1);
        cartService.addToCart(cloth2);

        assertEquals(2, cart.size());

        cartService.removeFromCart(cloth1);

        assertFalse(cart.contains(cloth1));
        assertTrue(cart.contains(cloth2));
        assertEquals(1, cart.size());
    }

}
