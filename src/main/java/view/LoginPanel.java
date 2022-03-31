package view;
import controller.LoginController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginPanel extends JFrame {

    private JPanel loginPanel = new JPanel();
    private JTextField emailTxt = new JTextField();
    private JPasswordField passwordTxt = new JPasswordField();
    private JLabel emailLbl = new JLabel("Email: ");
    private JLabel passwordLbl = new JLabel("Password: ");
    private JButton loginBtn = new JButton("Login");
    private JButton registerBtn = new JButton("Register");
    private JLabel versionLbl = new JLabel("Version 1.0.0.");
    BufferedImage img;

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);

    private LoginController loginController;

    public LoginPanel(int width, int height){
        loginController = new LoginController();

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setTitle("Primaria abc | Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        loginPanel.setLayout(null);
        loginPanel.setBounds(0, 0, width, height);
        loginPanel.setBackground(new Color(209, 246, 156, 255));

        emailLbl.setFont(normalFont);
        passwordLbl.setFont(normalFont);
        versionLbl.setFont(normalFont);
        loginBtn.setFont(normalFont);
        registerBtn.setFont(normalFont);

        emailLbl.setBounds(200, 200, 150, 100);
        passwordLbl.setBounds(200, 260, 150, 100);
        emailTxt.setBounds(310, 230, 300, 35);
        passwordTxt.setBounds(310, 290, 300, 35);
        loginBtn.setBounds(400, 340, 200, 60);
        registerBtn.setBounds(620, 200, 170, 150);
        versionLbl.setBounds(840, 450, 200, 100);

        try {
            File file = new File("src\\main\\resources\\town_logo.png");
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageIcon icon = new ImageIcon(img);
        JLabel iconLbl = new JLabel();
        iconLbl.setIcon(icon);
        iconLbl.setBounds(400, 20, 250, 250);

        loginPanel.add(emailLbl);
        loginPanel.add(passwordLbl);
        loginPanel.add(emailTxt);
        loginPanel.add(passwordTxt);
        loginPanel.add(loginBtn);
        loginPanel.add(registerBtn);
        loginPanel.add(versionLbl);
        loginPanel.add(iconLbl);

        loginBtn.addActionListener(e -> {
            loginController.login(emailTxt.getText(), passwordTxt.getPassword(), this);
        });
        registerBtn.addActionListener(e -> {
            loginController.register();
            setVisible(false);
        });

        add(loginPanel);
        setVisible(true);
    }

}