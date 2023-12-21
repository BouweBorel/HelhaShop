package com.helha.shop.controller;

import com.helha.shop.model.Size;
import com.helha.shop.service.CartService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.helha.shop.ShopApplication;
import com.helha.shop.MyListener;
import com.helha.shop.model.Cloth;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {

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
    private TextField searchBox;

    @FXML
    private ImageView cartView;

    @FXML
    private GridPane grid;

    @FXML
    private ComboBox comboSize;

    @FXML
    private ComboBox comboQuantity;

    @FXML
    private CartService myCart;

    @FXML
    private Text cartCounter;

    private Cloth selectedCloth = new Cloth();

    @FXML
    private void onSearchButtonClick() {
        clothes.clear();
        if (!searchBox.getText().isBlank()) {
            for(Cloth cloth : getData()) {
                if (cloth.getName().toLowerCase().contains(searchBox.getText().toLowerCase())) {
                    clothes.add(cloth);
                }
            }
//            clothes.addAll(getData().stream()
//                    .filter(cloth -> cloth.getName().toLowerCase().contains(searchBox.getText().toLowerCase()))
//                    .collect(Collectors.toList()));
        } else {
            clothes.addAll(getData());
        }
        displayItems();
    }

    @FXML
    private void onAddToCartButtonClick() {
        Cloth clothToAdd = new Cloth();
        clothToAdd.setQuantity(Integer.valueOf(comboQuantity.getValue().toString()));
        clothToAdd.setSize(Size.valueOf(comboSize.getValue().toString()));
        clothToAdd.setPrice(selectedCloth.getPrice());
        clothToAdd.setName(selectedCloth.getName());
        clothToAdd.setImgSrc(selectedCloth.getImgSrc());
        myCart.addToCart(clothToAdd);
        cartCounter.setText(myCart.getCart().isEmpty()?"":String.valueOf(myCart.getCart().size()));
    }

    @FXML
    private void goToCartView() {
        try {
            // Load AnotherScene.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cart.fxml"));
            Parent cartSceneRoot = loader.load();

            // Create a new scene with AnotherScene
            Scene cartScene = new Scene(cartSceneRoot);

            // Get the current stage
            Stage stage = (Stage) cartView.getScene().getWindow();

            // Set the new scene in the stage
            stage.setScene(cartScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Cloth> clothes = new ArrayList<>();

    private Image image;
    private MyListener myListener;

    private List<Cloth> getData() {
        List<Cloth> clothes = new ArrayList<>();
        Cloth cloth;

        cloth = new Cloth();
        cloth.setName("BSI");
        cloth.setPrice(26);
        cloth.setImgSrc("/images/bsi.png");
        cloth.setColor("1B1F52");
        clothes.add(cloth);

        cloth = new Cloth();
        cloth.setName("Biochimical Engineering");
        cloth.setPrice(29);
        cloth.setImgSrc("/images/be.png");
        cloth.setColor("1B1F52");
        clothes.add(cloth);

        cloth = new Cloth();
        cloth.setName("Chemical Engineering");
        cloth.setPrice(30);
        cloth.setImgSrc("/images/ce.png");
        cloth.setColor("1B1F52");
        clothes.add(cloth);

        cloth = new Cloth();
        cloth.setName("EM");
        cloth.setPrice(35);
        cloth.setImgSrc("/images/em.png");
        cloth.setColor("1B1F52");
        clothes.add(cloth);

        cloth = new Cloth();
        cloth.setName("EM ETS");
        cloth.setPrice(28);
        cloth.setImgSrc("/images/emets.png");
        cloth.setColor("1B1F52");
        clothes.add(cloth);

        cloth = new Cloth();
        cloth.setName("GE");
        cloth.setPrice(20);
        cloth.setImgSrc("/images/ge.png");
        cloth.setColor("1B1F52");
        clothes.add(cloth);


        cloth = new Cloth();
        cloth.setName("LDT");
        cloth.setPrice(29);
        cloth.setImgSrc("/images/ldt.png");
        cloth.setColor("1B1F52");
        clothes.add(cloth);


        cloth = new Cloth();
        cloth.setName("MAGA");
        cloth.setPrice(25);
        cloth.setImgSrc("/images/maga.png");
        cloth.setColor("1B1F52");
        clothes.add(cloth);


        cloth = new Cloth();
        cloth.setName("MAGP");
        cloth.setPrice(29);
        cloth.setImgSrc("/images/magp.png");
        cloth.setColor("1B1F52");
        clothes.add(cloth);

        cloth = new Cloth();
        cloth.setName("Mécanique");
        cloth.setPrice(29);
        cloth.setImgSrc("/images/me.png");
        cloth.setColor("1B1F52");
        clothes.add(cloth);

        cloth = new Cloth();
        cloth.setName("Automatique");
        cloth.setPrice(29);
        cloth.setImgSrc("/images/au.png");
        cloth.setColor("1B1F52");
        clothes.add(cloth);

        return clothes;
    }

    private void setChosenCloth(Cloth cloth) {
        selectedCloth.setName(cloth.getName());
        selectedCloth.setPrice(cloth.getPrice());
        selectedCloth.setColor(cloth.getColor());
        selectedCloth.setImgSrc(cloth.getImgSrc());
        clothNameLable.setText(cloth.getName());
        clothPriceLabel.setText(cloth.getPrice() + " " + ShopApplication.CURRENCY);
        java.net.URL imageUrl = getClass().getResource(cloth.getImgSrc());
        clothImg.setImage(new Image(imageUrl.toExternalForm()));
        chosenClothCard.setStyle("-fx-background-color: #" + cloth.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myCart = CartService.getINSTANCE();
        // Set the default value to the first item in the ComboBox
        comboSize.setValue(comboSize.getItems().get(2));
        comboQuantity.setValue(comboQuantity.getItems().get(0));
        cartCounter.setText(myCart.getCart().isEmpty()?"":String.valueOf(myCart.getCart().size()));
        // Get all clothes
        clothes.addAll(getData());
        // Display clothes
        displayItems();
    }

    private void displayItems() {
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        if (clothes.size() > 0) {
            setChosenCloth(clothes.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Cloth cloth) {
                    setChosenCloth(cloth);
                }
            };
        }
        try {
            for (int i = 0; i < clothes.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(clothes.get(i),myListener);

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
