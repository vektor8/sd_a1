package com.example.demo.ui;

import com.example.demo.model.dao.PackageDAO;
import com.example.demo.model.dao.RegularUserDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookingUI implements Initializable {

    @FXML
    private TableView bookingsTable;

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
        table.getItems().addAll(packageDAOList.stream().filter(packageDAO -> packageDAO.getAvailablePlaces() > 0).toList());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable(bookingsTable, user.getBookings());
    }
}
