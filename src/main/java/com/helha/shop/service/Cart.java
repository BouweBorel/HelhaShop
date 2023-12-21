package com.helha.shop.service;

import com.helha.shop.model.Cloth;

import java.util.List;

public interface Cart {

    public List<Cloth> addToCart(Cloth newCloth);
    public List<Cloth> removeFromCart(Cloth cloth);
    public List<Cloth> getCart();

    }
