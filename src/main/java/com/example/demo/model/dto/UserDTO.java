package com.example.demo.model.dto;

public class UserDTO {
    String email;
    String password;
    String checkPassword;

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDTO(String email, String password, String checkPassword) {
        this.email = email;
        this.password = password;
        this.checkPassword = checkPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
