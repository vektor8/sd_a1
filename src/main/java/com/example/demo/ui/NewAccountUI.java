package com.example.demo.ui;

import com.example.demo.HelloApplication;
import com.example.demo.controller.UserController;
import com.example.demo.model.dao.RegularUserDAO;
import com.example.demo.model.dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewAccountUI {

    UserController _userController = new UserController();
    @FXML
    private PasswordField checkPasswordField;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    void createAccount(ActionEvent event) throws Exception {
        RegularUserDAO newUser = _userController.newRegularUser(new UserDTO(emailTextField.getText(), passwordField.getText(), checkPasswordField.getText()));
        if (newUser != null) {
            HelloApplication.setRoot("hello-view", 320, 379);
        }else{
            errorLabel.setText("Your user data is not correct");
        }
    }
    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        HelloApplication.setRoot("hello-view", 320, 379);
    }
}
