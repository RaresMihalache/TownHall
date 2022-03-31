package view;

import controller.UserController;

import javax.swing.*;
import java.awt.*;

public class UserMainPanel extends JFrame {

    private JPanel userPanel = new JPanel();
    private JButton backBtn = new JButton("\u2BA8");
    private JLabel welcomeLbl = new JLabel("Welcome ");
    private JButton addRemoveHouseBtn = new JButton("<html>Add/ remove <br> locuinte <br> personale</html>");
    private JButton crudRequestsBtn = new JButton("<html>Add/ update/ <br> delete requests</html>");
    private JLabel versionLbl = new JLabel("Version 1.0.0.");

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);
    private Font mainBtnFont = new Font("Serif", Font.PLAIN, 30);
    private Font backBtnFont = new Font("Serif", Font.PLAIN, 55);

    private UserController userController;

    public UserMainPanel(int width, int height){
        userController = new UserController();

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setTitle("Primaria abc | User Main panel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        userPanel.setLayout(null);
        userPanel.setBounds(0, 0, width, height);
        userPanel.setBackground(new Color(177, 218, 125));

        welcomeLbl.setFont(normalFont);
        versionLbl.setFont(normalFont);
        backBtn.setFont(backBtnFont);
        addRemoveHouseBtn.setFont(mainBtnFont);
        crudRequestsBtn.setFont(mainBtnFont);

        welcomeLbl.setBounds(600, 50, 150, 100);
        versionLbl.setBounds(1040, 600, 150, 100);
        backBtn.setBounds(50, 50, 150, 75);
        addRemoveHouseBtn.setBounds(50, 200, 400, 300);
        crudRequestsBtn.setBounds(650, 200, 400, 300);

        userPanel.add(welcomeLbl);
        userPanel.add(versionLbl);
        userPanel.add(backBtn);
        userPanel.add(addRemoveHouseBtn);
        userPanel.add(crudRequestsBtn);

        add(userPanel);
        setVisible(true);

        backBtn.addActionListener(e -> {
            setVisible(false);
            userController.back();
        });

        addRemoveHouseBtn.addActionListener(e -> {
            setVisible(false);
            var view = new UserAddHousePanel(1200, 700);
            userController.addRemoveHouse();
        });

        crudRequestsBtn.addActionListener(e -> {
            setVisible(false);
            var view = new UserCrudReqPanel(1200, 700);
        });
    }

}
