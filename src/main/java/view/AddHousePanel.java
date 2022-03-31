package view;

import javax.swing.*;
import java.awt.*;
import controller.UserController;

public class AddHousePanel extends JFrame {
    private UserController userController;

    private JPanel addHousePanel = new JPanel();
    private JLabel streetLbl = new JLabel("Street:");
    private JLabel noLbl = new JLabel("No:");
    private JLabel blLbl = new JLabel("Bl:");
    private JLabel apLbl = new JLabel("Ap:");
    private JTextField streetTxt = new JTextField();
    private JTextField noTxt = new JTextField();
    private JTextField blTxt = new JTextField();
    private JTextField apTxt = new JTextField();
    private JButton addBtn = new JButton("Add");

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);

    public AddHousePanel(int width, int height){
        userController = new UserController();

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setTitle("Add house");

        addHousePanel.setLayout(null);
        addHousePanel.setBounds(0, 0, width, height);
        addHousePanel.setBackground(new Color(177, 218, 125));

        streetLbl.setFont(normalFont);
        noLbl.setFont(normalFont);
        blLbl.setFont(normalFont);
        apLbl.setFont(normalFont);
        streetTxt.setFont(normalFont);
        noTxt.setFont(normalFont);
        blTxt.setFont(normalFont);
        apTxt.setFont(normalFont);
        addBtn.setFont(normalFont);

        streetLbl.setBounds(50, 50, 200, 75);
        noLbl.setBounds(70, 100, 200, 75);
        blLbl.setBounds(70, 150, 200, 75);
        apLbl.setBounds(70, 200, 200, 75);
        streetTxt.setBounds(200, 70, 450, 45);
        noTxt.setBounds(200, 120, 450, 45);
        blTxt.setBounds(200, 170, 450, 45);
        apTxt.setBounds(200, 220, 450, 45);
        addBtn.setBounds(250, 350, 200, 50);

        addHousePanel.add(streetLbl);
        addHousePanel.add(streetTxt);
        addHousePanel.add(noLbl);
        addHousePanel.add(noTxt);
        addHousePanel.add(blLbl);
        addHousePanel.add(blTxt);
        addHousePanel.add(apLbl);
        addHousePanel.add(apTxt);
        addHousePanel.add(addBtn);

        addBtn.addActionListener(e -> {
            userController.addHouse(
                    streetTxt.getText(),
                    noTxt.getText(),
                    blTxt.getText(),
                    apTxt.getText()
            );
        });

        add(addHousePanel);
        setVisible(true);
    }
}
