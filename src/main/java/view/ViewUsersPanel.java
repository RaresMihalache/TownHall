package view;

import controller.AdminController;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewUsersPanel extends JFrame{
    private AdminController adminController;

    private  JPanel viewUsersPanel = new JPanel();
    private JPanel tablePanel = new JPanel();
    private JButton backBtn = new JButton("\u2BA8");
    private JTable usersTable;
    private Object[] columns;
    private Object[][] data;

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);
    private Font backBtnFont = new Font("Serif", Font.PLAIN, 55);

    public ViewUsersPanel(int width, int height){
        adminController = new AdminController();

        columns = adminController.viewUsersFields().toArray();
        ArrayList<User> users = adminController.viewUsersData();
        data = new Object[users.size()][columns.length];
        for(int i = 0; i < users.size(); i ++){
            data[i][0] = users.get(i).getId();
            data[i][1] = users.get(i).getEmail();
            data[i][2] = users.get(i).getUsername();
            data[i][3] = users.get(i).getPassword();
        }

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setTitle("Primaria abc | Admin Main panel > View users");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        viewUsersPanel.setLayout(null);
        viewUsersPanel.setBounds(0, 0, width, height);
        viewUsersPanel.setBackground(new Color(177, 218, 125));

        backBtn.setFont(backBtnFont);

        backBtn.setBounds(50, 50, 150, 75);
        tablePanel.setLayout(new GridLayout(1, 0));
        tablePanel.setBounds(100, 150, width - 200, height - 250);

        usersTable = new JTable(data, columns);
        tablePanel.add(new JScrollPane(usersTable));
        tablePanel.setVisible(true);

        viewUsersPanel.add(backBtn);
        viewUsersPanel.add(tablePanel);

        backBtn.addActionListener(e -> {
            setVisible(false);
            var view = new AdminMainPanel(1200, 700);
        });

        add(viewUsersPanel);
        setVisible(true);
    }

}
