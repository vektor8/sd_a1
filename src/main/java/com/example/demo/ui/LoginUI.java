package com.example.demo.ui;

import com.example.demo.HelloApplication;
import com.example.demo.controller.UserController;
import com.example.demo.model.dao.RegularUserDAO;
import com.example.demo.model.dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginUI {

    private final UserController _userController = new UserController();
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    void login(ActionEvent event) throws IOException {
            RegularUserDAO user = _userController.login(new UserDTO(emailTextField.getText(), passwordTextField.getText()));
            if(user != null) {
                HelloApplication.setRoot("user-view", 1100, 700);
                RegularUserUI.user = user;
            }
    }

    @FXML
    void switchToNewAccountScreen(ActionEvent event) throws IOException {
        HelloApplication.setRoot("new-account", 500, 500);
    }


}
