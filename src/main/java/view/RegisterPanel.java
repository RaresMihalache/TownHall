package view;

import controller.RegisterController;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JFrame {

    private JButton backBtn = new JButton("\u2BA8");
    private JButton createAccountBtn = new JButton("Create account");
    private JLabel emailLbl = new JLabel("Email:");
    private JLabel usernameLbl = new JLabel("Username:");
    private JLabel passwordLbl = new JLabel("Password:");
    private JLabel passwordConfirmLbl = new JLabel("Confirm password:");
    private JLabel versionLbl = new JLabel("Version 1.0.0.");
    private JTextField emailTxt = new JTextField();
    private JTextField usernameTxt = new JTextField();
    private JPasswordField passwordTxt = new JPasswordField();
    private JPasswordField passwordConfirmTxt = new JPasswordField();
    private JPanel registerPanel = new JPanel();

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);
    private Font backBtnFont = new Font("Serif", Font.PLAIN, 55);

    private RegisterController registerController;

    public RegisterPanel(int width, int height){
        registerController = new RegisterController();

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setTitle("Primaria abc | Register");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        registerPanel.setLayout(null);
        registerPanel.setBounds(0, 0, width, height);
        registerPanel.setBackground(new Color(177, 218, 125, 224));

        emailLbl.setFont(normalFont);
        usernameLbl.setFont(normalFont);
        passwordLbl.setFont(normalFont);
        passwordConfirmLbl.setFont(normalFont);
        versionLbl.setFont(normalFont);
        backBtn.setFont(backBtnFont);
        createAccountBtn.setFont(normalFont);


        backBtn.setBounds(50, 50, 150, 75);
        createAccountBtn.setBounds(700, 500, 250, 100);
        emailLbl.setBounds(200, 200, 150, 100);
        usernameLbl.setBounds(200, 260, 150, 100);
        passwordLbl.setBounds(200, 320, 150, 100);
        passwordConfirmLbl.setBounds(200, 380, 250, 100);
        versionLbl.setBounds(1040, 600, 150, 100);
        emailTxt.setBounds(450, 230, 300, 35);
        usernameTxt.setBounds(450, 290, 300, 35);
        passwordTxt.setBounds(450, 350, 300, 35);
        passwordConfirmTxt.setBounds(450, 410, 300, 35);


        registerPanel.add(backBtn);
        registerPanel.add(createAccountBtn);
        registerPanel.add(emailLbl);
        registerPanel.add(emailTxt);
        registerPanel.add(usernameLbl);
        registerPanel.add(usernameTxt);
        registerPanel.add(passwordLbl);
        registerPanel.add(passwordTxt);
        registerPanel.add(passwordConfirmLbl);
        registerPanel.add(passwordConfirmTxt);
        registerPanel.add(versionLbl);

        backBtn.addActionListener(e -> {
            registerController.back();
            setVisible(false);
        });
        createAccountBtn.addActionListener(e -> {
            registerController.createAccount(emailTxt.getText(), usernameTxt.getText(),
                    passwordTxt.getPassword(), passwordConfirmTxt.getPassword());
        });

        add(registerPanel);
        setVisible(true);

    }

}
