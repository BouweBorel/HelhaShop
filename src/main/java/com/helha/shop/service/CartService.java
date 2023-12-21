package com.helha.shop.service;

import com.helha.shop.model.Cloth;

import java.util.ArrayList;
import java.util.List;

public class CartService implements Cart {

    private CartService() {

    }

    private static CartService INSTANCE = new CartService();

    public static CartService getINSTANCE() {
        return INSTANCE;
    }

    private List<Cloth> clothes = new ArrayList<>();
    private long total;

    public List<Cloth> addToCart(Cloth newCloth) {
        if (clothes.contains(newCloth)) {
            for (Cloth cloth : clothes) {
                if (cloth.getName().equals(newCloth.getName()) && cloth.getSize().equals(newCloth.getSize())) {
                    cloth.setQuantity(cloth.getQuantity() + newCloth.getQuantity());
                }
            }

        } else {
            clothes.add(newCloth);
        }
        return clothes;
    }

    public List<Cloth> removeFromCart(Cloth cloth) {
        clothes.remove(cloth);
        return clothes;
    }

    public List<Cloth> getCart() {
        return clothes;
    }

}
