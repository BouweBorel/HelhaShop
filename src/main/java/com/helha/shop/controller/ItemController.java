package com.helha.shop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import com.helha.shop.ShopApplication;
import com.helha.shop.MyListener;
import com.helha.shop.model.Cloth;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(cloth);
    }

    private Cloth cloth;
    private MyListener myListener;

    public void setData(Cloth cloth, MyListener myListener) {
        this.cloth = cloth;
        this.myListener = myListener;
        nameLabel.setText(cloth.getName());
        priceLable.setText(cloth.getPrice()+ " " + ShopApplication.CURRENCY);
        Image image = new Image(getClass().getResourceAsStream(cloth.getImgSrc()));
        img.setImage(image);
    }
}
