package com.example.demo.ui;

import com.example.demo.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.Optional;

public class NewAccountController {

    UserService _userService = new UserService();

    @FXML
    private TextField checkPasswordTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private CheckBox userTypeCheckBox;

//    @FXML
//    void createAccount(ActionEvent event) throws Exception {
//        UserDAO a= _userService.newAccount(emailTextField.getText(), passwordTextField.getText(), checkPasswordTextField.getText(), userTypeCheckBox.isSelected());
//    }

}
