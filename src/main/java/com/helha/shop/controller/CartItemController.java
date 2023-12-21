package com.helha.shop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.helha.shop.MyListener;
import com.helha.shop.ShopApplication;
import com.helha.shop.model.Cloth;

public class CartItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView img;

    @FXML
    private Label quantityLabel;

    @FXML
    private Label sizeLabel;

    @FXML
    private Label deleteLabel;

    @FXML
    public void onDeleteLabelClick() {
        myListener.onClickListener(cloth);
    }

    private Cloth cloth;
    private MyListener myListener;

    public void setData(Cloth cloth, MyListener myListener) {
        this.cloth = cloth;
        this.myListener = myListener;
        nameLabel.setText(cloth.getName());
        priceLabel.setText(cloth.getPrice() + " " + ShopApplication.CURRENCY);
        quantityLabel.setText(String.valueOf(cloth.getQuantity()));
        sizeLabel.setText(cloth.getSize().toString());
        Image image = new Image(getClass().getResourceAsStream(cloth.getImgSrc()));
        img.setImage(image);
    }
}
