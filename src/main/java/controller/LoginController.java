package controller;

import Constants.Constants;
import antlr.StringUtils;
import entity.User;
import service.AdminService;
import service.UserService;
import view.AdminMainPanel;
import view.LoginPanel;
import view.RegisterPanel;
import view.UserMainPanel;

import javax.swing.*;
public class LoginController {


    private UserService userService;
    private AdminService adminService;

    public LoginController(){
        userService = new UserService();
        adminService = new AdminService();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public void login(String email, char[] passwordCharArr, LoginPanel loginView) {
        String passwordStr = String.valueOf(passwordCharArr); // converts char[] -> String
        System.out.println(passwordStr);
        if(userService.login(email, passwordStr) == 2){
            JOptionPane.showMessageDialog(null, Constants.WELCOME_LOGIN_ADMIN, "Logare | Admin", JOptionPane.INFORMATION_MESSAGE);
            var view = new AdminMainPanel(1200, 700);
            loginView.setVisible(false);

        }
        else if(userService.login(email, passwordStr) == 1){
            JOptionPane.showMessageDialog(null, Constants.WELCOME_LOGIN_USER, "Logare | User", JOptionPane.INFORMATION_MESSAGE);
            var view = new UserMainPanel(1200, 700);
            loginView.setVisible(false);
            User loggedUser = userService.getLoggedUser(email, passwordStr);
            //userService.setEmailService(email);
            userService.setUser(loggedUser);
        }
        else{
            JOptionPane.showMessageDialog(null, Constants.INVALID_CREDENTIALS, "Logare | Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void register(){
        var view = new RegisterPanel(1200, 700);
    }
}
