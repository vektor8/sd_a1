package com.example.demo.service;

import com.example.demo.model.dao.RegularUserDAO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repository.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository _userRepository = new UserRepository();

    public static boolean validEmail(String str) {
        return str.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
    }

    public RegularUserDAO login(UserDTO user) throws Exception {
        Optional<RegularUserDAO> loggedUser = _userRepository.checkCredentials(user);
        if (loggedUser.isPresent()) {
            return loggedUser.get();
        } else {
            throw new Exception("User credentials are not correct");
        }
    }

    public RegularUserDAO newAccount(UserDTO user) throws Exception {
        if (!validEmail(user.getEmail()) || !user.getPassword().equals(user.getCheckPassword())) {
            throw new Exception("Invalid new account data");
        }
        Optional<RegularUserDAO> returnUser;
        returnUser = _userRepository.saveRegularUser(user);
        if (returnUser.isPresent()) {
            return returnUser.get();
        }
        throw new Exception("user couldn't be saved to db");
    }
}
