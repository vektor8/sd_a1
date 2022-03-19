package com.example.demo.ui;

import com.example.demo.controller.PackageController;
import com.example.demo.controller.UserController;
import com.example.demo.model.dao.PackageDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class RegularUserUI implements Initializable {
    UserController _userController = new UserController();
    @FXML
    private ComboBox DestinationDropdown;

    @FXML
    private TableView PackageTable;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField maxPrice;

    @FXML
    private TextField minPrice;

    @FXML
    private DatePicker startDate;

    public void updateTable(TableView table, List<PackageDAO> packageDAOList) {
        table.getColumns().clear();
        table.getItems().clear();
        for (Field field : PackageDAO.class.getDeclaredFields()) {
            if(field.getName().equals("tourists"))
                continue;
            field.setAccessible(true);
            TableColumn t = new TableColumn(field.getName());
            t.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            table.getColumns().add(t);
            t.setMinWidth(100);
            t.setStyle( "-fx-alignment: CENTER;");
        }
        ContextMenu cm = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Book");
        menuItem1.setOnAction(e -> {
            System.out.println("he wanna book it");
        });
        cm.getItems().addAll(menuItem1);

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                cm.show(table, event.getScreenX(), event.getScreenY());
            }
        });
        table.getItems().addAll(packageDAOList.stream().filter(packageDAO -> packageDAO.getAvailablePlaces() > 0).toList());
    }

    public void filterPackages(){
        Date maxDate = new Date(1647717369);
        Date minDate = new Date(0);
        Double minPrice = 0d;
        Double maxPrice = Double.MAX_VALUE;
//        if(startDate.)
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable(PackageTable, _userController.getAllPackages());
        minPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            filterPackages();
        });

        maxPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            filterPackages();
        });

        startDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            filterPackages();
        });

        endDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            filterPackages();
        });
        DestinationDropdown.getItems().addAll(FXCollections.observableArrayList(_userController.getAllDestinations()));
    }
}
