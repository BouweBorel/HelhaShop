module com.helha.shop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.helha.shop to javafx.fxml;
    exports com.helha.shop;
    exports com.helha.shop.controller;
    opens com.helha.shop.controller;
}