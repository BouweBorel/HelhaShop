package com.helha.shop.model;

import java.util.Objects;

public class Cloth {
    private String name;
    private String imgSrc;
    private double price;
    private String color;
    private int quantity;
    private Size size;

    public Cloth() {
    }

    public Cloth(String newName, double newPrice, int newQuantity, Size newSize) {
        name = newName;
        price = newPrice;
        quantity = newQuantity;
        size = newSize;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cloth cloth = (Cloth) obj;
        boolean isTrue = name.equals(cloth.name) && size.equals(cloth.getSize());
        return name.equals(cloth.name) && size.equals(cloth.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
