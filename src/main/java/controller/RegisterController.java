package controller;

import Constants.Constants;
import service.UserService;
import validator.GeneralValidator;
import view.LoginPanel;

import javax.swing.*;

public class RegisterController {

    private UserService userService;

    public RegisterController(){
        userService = new UserService();
    }

    public void back(){
        var view = new LoginPanel(1000, 550);
    }

    public void createAccount(String email, String username, char[] passwordCharArr, char[] confirmPasswordCharArr){
        String passwordStr = String.valueOf(passwordCharArr);
        String confirmPasswordStr = String.valueOf(confirmPasswordCharArr);

        int registerValue = userService.register(email, username, passwordStr, confirmPasswordStr);
        switch (registerValue){
            case -4:
                JOptionPane.showMessageDialog(null, Constants.INVALID_EMAIL, "Register | Email error", JOptionPane.ERROR_MESSAGE);
                break;
            case -3:
                JOptionPane.showMessageDialog(null, Constants.REGISTER_PASSWORD_EMPTY, "Register | Password error", JOptionPane.ERROR_MESSAGE);
                break;
            case -2:
                JOptionPane.showMessageDialog(null, Constants.REGISTER_USERNAME_EMPTY, "Register | Username error", JOptionPane.ERROR_MESSAGE);
                break;
            case -1:
                JOptionPane.showMessageDialog(null, Constants.REGISTER_FAIL_PASSWORD_CONFIRMATION, "Register | Password error", JOptionPane.ERROR_MESSAGE);
                break;
            case 0:
                JOptionPane.showMessageDialog(null, Constants.REGISTER_FAIL_EMAIL, "Register | Email error", JOptionPane.ERROR_MESSAGE);
                break;
            case 1:
                JOptionPane.showMessageDialog(null, Constants.REGISTER_SUCCESS, "Register | Success", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
}
