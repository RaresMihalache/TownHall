package view;

import javax.swing.*;
import java.awt.*;
import controller.UserController;

public class DeleteHousePanel extends JFrame {
    private UserController userController;

    private JPanel deleteHousePanel = new JPanel();
    private JButton okBtn = new JButton("Delete");
    private JLabel infoLbl = new JLabel("Please type the id of the house you want to delete");
    private JTextField idTxt = new JTextField();

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);

    public DeleteHousePanel(int width, int height){
        userController = new UserController();

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setTitle("Delete house");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);

        deleteHousePanel.setLayout(null);
        deleteHousePanel.setBounds(0, 0, width, height);
        deleteHousePanel.setBackground(new Color(177, 218, 125));

        infoLbl.setFont(normalFont);
        okBtn.setFont(normalFont);
        idTxt.setFont(normalFont);

        infoLbl.setBounds(50, 50, 550, 75);
        idTxt.setBounds(100, 150, 400, 45);
        okBtn.setBounds(250, 270, 100, 75);

        deleteHousePanel.add(infoLbl);
        deleteHousePanel.add(idTxt);
        deleteHousePanel.add(okBtn);

        okBtn.addActionListener(e -> {
            userController.removeHouse(idTxt.getText());
        });

        add(deleteHousePanel);
        setVisible(true);
    }
}
