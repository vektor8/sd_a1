package com.example.demo.ui;

import com.example.demo.HelloApplication;
import com.example.demo.controller.AgencyController;
import com.example.demo.model.dao.AgencyDAO;
import com.example.demo.model.dao.DestinationDAO;
import com.example.demo.model.dao.PackageDAO;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AgencyUI implements Initializable {
    public TableView destinationsTable;
    public TableView packagesTable;

    public static  AgencyDAO agency;
    private final AgencyController _agencyController = new AgencyController();

    public void updatePackagesTable(TableView table, List<PackageDAO> list) {
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
        MenuItem menuItem1 = new MenuItem("Delete");
        MenuItem menuItem2 = new MenuItem("Add");
        MenuItem menuItem3 = new MenuItem("Edit");
        TableView finalTable = table;
        menuItem1.setOnAction(e -> {
            _agencyController.deletePackage( ((PackageDAO)finalTable.getSelectionModel().getSelectedItem()).getId());
            updatePackagesTable(packagesTable, _agencyController.getPackagesByAgencyID(agency.getId()));
        });
        PackageUI.agency = agency;
        menuItem2.setOnAction(e->{
            PackageUI.pack = null;
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = null;
            try {
                scene = new Scene(HelloApplication.loadFXML("add-package"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            stage.setScene(scene);
            stage.showAndWait();
            updatePackagesTable(packagesTable, _agencyController.getPackagesByAgencyID(agency.getId()));
        });

        menuItem3.setOnAction(e->{
            PackageUI.pack = ((PackageDAO) table.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = null;
            try {
                scene = new Scene(HelloApplication.loadFXML("add-package"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            stage.setScene(scene);
            stage.showAndWait();
            updatePackagesTable(packagesTable, _agencyController.getPackagesByAgencyID(agency.getId()));
        });
        cm.getItems().addAll(menuItem1, menuItem2, menuItem3);

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                cm.show(table, event.getScreenX(), event.getScreenY());
            }
        });
        table.getItems().addAll(list);
    }

    public void updateDestinationsTable(TableView table, List<DestinationDAO> list) {
        table.getColumns().clear();
        table.getItems().clear();
        for (Field field : DestinationDAO.class.getDeclaredFields()) {
            if (field.getName().equals("packages"))
                continue;
            field.setAccessible(true);
            TableColumn t = new TableColumn(field.getName());
            t.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            table.getColumns().add(t);
            t.setMinWidth(100);
            t.setStyle("-fx-alignment: CENTER;");
        }
        ContextMenu cm = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Delete");
        MenuItem menuItem2 = new MenuItem("Add");
        menuItem1.setOnAction(e -> {
            _agencyController.deleteDestination( ((DestinationDAO) table.getSelectionModel().getSelectedItem()).getId());
            updateDestinationsTable(destinationsTable, _agencyController.getAllDestinations());
            updatePackagesTable(packagesTable, _agencyController.getPackagesByAgencyID(agency.getId()));
        });
        menuItem2.setOnAction(e -> {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = null;
            try {
                scene = new Scene(HelloApplication.loadFXML("destination"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            stage.setScene(scene);
            stage.showAndWait();
            updateDestinationsTable(destinationsTable, _agencyController.getAllDestinations());
        });
        cm.getItems().addAll(menuItem1, menuItem2);

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                cm.show(table, event.getScreenX(), event.getScreenY());
            }
        });
        table.getItems().addAll(list);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("da ba");
        updatePackagesTable(packagesTable, _agencyController.getPackagesByAgencyID(agency.getId()));
        updateDestinationsTable(destinationsTable, _agencyController.getAllDestinations());
    }
}
