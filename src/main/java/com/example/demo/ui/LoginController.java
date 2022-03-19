package com.example.demo.ui;

import com.example.demo.HelloApplication;
import com.example.demo.model.dao.RegularUserDAO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    private final UserService _userService = new UserService();
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    void login(ActionEvent event) {
        try {
            RegularUserDAO user = _userService.login(new UserDTO(emailTextField.getText(), passwordTextField.getText()));
            HelloApplication.setRoot("user-view", 500,500);
        }catch (Exception e){
            e.printStackTrace();
        }

//       Optional<UserDAO> a =  _userService.login(new UserDTO(emailTextField.getText(), passwordTextField.getText()));
//       System.out.println(a);
    }

    @FXML
    void switchToNewAccountScreen(ActionEvent event) throws IOException {
        HelloApplication.setRoot("new-account", 500, 500);
    }


}
