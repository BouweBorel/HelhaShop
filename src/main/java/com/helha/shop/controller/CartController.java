package com.helha.shop.controller;

import com.helha.shop.service.CartService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.helha.shop.MyListener;
import com.helha.shop.model.Cloth;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    @FXML
    private VBox chosenClothCard;

    @FXML
    private Label clothNameLable;

    @FXML
    private Label clothPriceLabel;

    @FXML
    private ImageView clothImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private Label itemsLabel;

    @FXML
    private Label totalLabel;

    private CartService myCart;

    @FXML
    private void goToMarketView() {
        try {
            // Load AnotherScene.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/market.fxml"));
            Parent marketSceneRoot = loader.load();

            // Create a new scene with AnotherScene
            Scene marketScene = new Scene(marketSceneRoot);

            // Get the current stage
            Stage stage = (Stage) grid.getScene().getWindow();

            // Set the new scene in the stage
            stage.setScene(marketScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image image;
    private MyListener myListener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myCart = CartService.getINSTANCE();
        displayItems();
    }

    private void displayItems() {
        int column = 0;
        int row = 1;
        int total = 0;
        grid.getChildren().clear();
        if (myCart.getCart().size() > 0) {
            myListener = new MyListener() {
                @Override
                public void onClickListener(Cloth cloth) {
                    myCart.removeFromCart(cloth);
                    displayItems();
                }
            };
        }
        try {
            for (int i = 0; i < myCart.getCart().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/cartItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CartItemController cartItemController = fxmlLoader.getController();
                cartItemController.setData(myCart.getCart().get(i), myListener);


                total += myCart.getCart().get(i).getQuantity() * myCart.getCart().get(i).getPrice();
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
            totalLabel.setText(String.valueOf(total) + " €");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
