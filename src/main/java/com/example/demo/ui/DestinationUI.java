package com.example.demo.ui;

import com.example.demo.controller.AgencyController;
import com.example.demo.model.dto.DestinationDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DestinationUI {

    private final AgencyController _agencyController = new AgencyController();
    @FXML
    TextField nameField;

    @FXML
    void addDestination(ActionEvent event) {
        _agencyController.createDestination(new DestinationDTO(nameField.getText()));
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

}
