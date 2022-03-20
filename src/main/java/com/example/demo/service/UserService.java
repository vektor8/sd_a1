package com.example.demo.service;

import com.example.demo.model.dao.RegularUserDAO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repository.UserRepository;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class UserService {

    private final UserRepository _userRepository = new UserRepository();

    public static boolean validEmail(String str) {
        return str.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
    }

    public static String encrypt(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public RegularUserDAO login(UserDTO user) throws Exception {
        user.setPassword(encrypt(user.getPassword()));
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
        user.setPassword(encrypt(user.getPassword()));
        returnUser = _userRepository.saveRegularUser(user);
        if (returnUser.isPresent()) {
            return returnUser.get();
        }
        throw new Exception("user couldn't be saved to db");
    }

    public RegularUserDAO getUserByID(Long id) throws Exception {
        Optional<RegularUserDAO> returnUser;
        returnUser = Optional.ofNullable(_userRepository.getUserByID(id));
        if (returnUser.isPresent()) {
            return returnUser.get();
        }
        throw new Exception("user couldn't be saved to db");
    }
}
