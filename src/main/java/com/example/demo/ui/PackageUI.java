package com.example.demo.ui;

import com.example.demo.controller.AgencyController;
import com.example.demo.model.dao.AgencyDAO;
import com.example.demo.model.dao.DestinationDAO;
import com.example.demo.model.dao.PackageDAO;
import com.example.demo.model.dto.PackageDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class PackageUI implements Initializable {

    public static AgencyDAO agency;
    public static PackageDAO pack;
    private final AgencyController _agencyController = new AgencyController();
    @FXML
    private TextField availableField;

    @FXML
    private ComboBox<DestinationDAO> destinationDropdown;

    @FXML
    private TextArea detailsField;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private DatePicker startDate;

    @FXML
    void addPackage(ActionEvent event) {
        PackageDTO packDTO = new PackageDTO(nameField.getText(),
                Double.parseDouble(priceField.getText()),
                Date.valueOf(startDate.getValue()), Date.valueOf(endDate.getValue()), detailsField.getText(),
                destinationDropdown.getValue().getId(),
                agency.getId(),
                Integer.parseInt(availableField.getText())
        );
        if (pack == null)
            _agencyController.createPackage(packDTO);
        else
            _agencyController.updatePackage(pack.getId(), packDTO);
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        destinationDropdown.getItems().addAll(_agencyController.getAllDestinations());
        if(pack!=null){
            nameField.setText(pack.getName());
            priceField.setText(pack.getPrice()+"");
            startDate.setValue(pack.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            endDate.setValue(pack.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            detailsField.setText(pack.getExtraDetails());
            destinationDropdown.getSelectionModel().select(pack.getDestinationDAO());
        }
    }
}
