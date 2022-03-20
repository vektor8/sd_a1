package com.example.demo.ui;

import com.example.demo.HelloApplication;
import com.example.demo.controller.UserController;
import com.example.demo.model.dao.RegularUserDAO;
import com.example.demo.model.dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class NewAccountUI {

    UserController _userController = new UserController();
    @FXML
    private TextField checkPasswordTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private CheckBox userTypeCheckBox;

    @FXML
    void createAccount(ActionEvent event) throws Exception {
        RegularUserDAO newUser = _userController.newRegularUser(new UserDTO(emailTextField.getText(), passwordTextField.getText(), checkPasswordTextField.getText()));
        if (newUser != null) {
            HelloApplication.setRoot("hello-view", 500, 500);
        }
    }

}
