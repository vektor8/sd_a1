package com.example.demo.ui;

import com.example.demo.HelloApplication;
import com.example.demo.controller.UserController;
import com.example.demo.model.dao.DestinationDAO;
import com.example.demo.model.dao.PackageDAO;
import com.example.demo.model.dao.RegularUserDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

    public static RegularUserDAO user;

    public void updateTable(TableView table, List<PackageDAO> packageDAOList) {
        table.getColumns().clear();
        table.getItems().clear();
        for (Field field : PackageDAO.class.getDeclaredFields()) {
            if (field.getName().equals("tourists"))
                continue;
            field.setAccessible(true);
            TableColumn t = new TableColumn(field.getName());
            t.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            table.getColumns().add(t);
            t.setMinWidth(100);
            t.setStyle("-fx-alignment: CENTER;");
        }
        ContextMenu cm = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Book");
        TableView finalTable = table;
        menuItem1.setOnAction(e -> {
            _userController.bookVacation(user.getId(), ((PackageDAO)finalTable.getSelectionModel().getSelectedItem()).getId());
            user = _userController.updateUser(user.getId());
            updateTable(PackageTable, _userController.getAllPackages());
        });
        cm.getItems().addAll(menuItem1);

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                cm.show(table, event.getScreenX(), event.getScreenY());
            }
        });
        table.getItems().addAll(packageDAOList.stream().filter(packageDAO -> packageDAO.getAvailablePlaces() > 0).toList());
    }

    public void filterPackages() {
        Date minDateFilter = startDate.getValue() != null ? Date.valueOf(startDate.getValue()) : new Date(0);
        Date maxDateFilter = endDate.getValue() != null ? Date.valueOf(endDate.getValue()) : new Date(1647717369);
        Double minPriceFilter = Double.MIN_VALUE, maxPriceFilter = Double.MAX_VALUE;
        try {
            if (!minPrice.getText().isBlank())
                minPriceFilter = Double.parseDouble(minPrice.getText());
            if (!maxPrice.getText().isBlank())
                maxPriceFilter = Double.parseDouble(maxPrice.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (DestinationDropdown.getSelectionModel().isEmpty())
            updateTable(PackageTable, _userController.getFilteredPackages(minPriceFilter, maxPriceFilter, minDateFilter, maxDateFilter));
        else {
            DestinationDAO a = (DestinationDAO) DestinationDropdown.getValue();
            updateTable(PackageTable, _userController.getFilteredPackages(minPriceFilter, maxPriceFilter, minDateFilter, maxDateFilter, a.getId()));
        }
    }

    @FXML
    void viewBooking() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        BookingUI.user = user;
        Scene scene = new Scene(HelloApplication.loadFXML("booking-view"));
        stage.setScene(scene);
        stage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable(PackageTable, _userController.getAllPackages());

        minPrice.textProperty().addListener((observable, oldValue, newValue) -> filterPackages());
        maxPrice.textProperty().addListener((observable, oldValue, newValue) -> filterPackages());
        startDate.valueProperty().addListener((observable, oldValue, newValue) -> filterPackages());
        endDate.valueProperty().addListener((observable, oldValue, newValue) -> filterPackages());

        DestinationDropdown.getItems().addAll(FXCollections.observableArrayList(_userController.getAllDestinations()));
    }
}
