package view;

import controller.UserController;
import entity.House;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserAddHousePanel extends JFrame {
    private UserController userController;

    private JPanel addRemoveHousePanel = new JPanel();
    private JPanel tablePanel = new JPanel();
    private JButton backBtn = new JButton("\u2BA8");
    private JButton deleteBtn = new JButton("X");
    private JButton addBtn = new JButton("V");
    private JTable housesTable;
    private Object[] columns;
    private Object[][] data;

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);
    private Font backBtnFont = new Font("Serif", Font.PLAIN, 55);

    public UserAddHousePanel(int width, int height){
        userController = new UserController();

        columns = userController.viewHousesFields().toArray();
        ArrayList<House> houses = userController.viewHousesData();
        data = new Object[houses.size()][columns.length];
        for(int i = 0; i < houses.size(); i ++){
            data[i][0] = houses.get(i).getId();
            data[i][1] = houses.get(i).getStreet();
            data[i][2] = houses.get(i).getNo();
            data[i][3] = houses.get(i).getBl();
            data[i][4] = houses.get(i).getAp();
            data[i][5] = houses.get(i).getUser().getId();
        }

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setTitle("Primaria abc | User Main panel > Add/remove houses");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addRemoveHousePanel.setLayout(null);
        addRemoveHousePanel.setBounds(0, 0, width, height);
        addRemoveHousePanel.setBackground(new Color(177, 218, 125));

        backBtn.setFont(backBtnFont);
        deleteBtn.setFont(normalFont);
        addBtn.setFont(normalFont);

        backBtn.setBounds(50, 50, 150, 75);
        deleteBtn.setBounds(450, 50, 150, 75);
        addBtn.setBounds(850, 50, 150, 75);

        tablePanel.setLayout(new GridLayout(1, 0));
        tablePanel.setBounds(100, 150, width - 200, height - 250);

        housesTable = new JTable(data, columns);
        tablePanel.add(new JScrollPane(housesTable));
        tablePanel.setVisible(true);

        addRemoveHousePanel.add(backBtn);
        addRemoveHousePanel.add(deleteBtn);
        addRemoveHousePanel.add(addBtn);
        addRemoveHousePanel.add(tablePanel);

        backBtn.addActionListener(e -> {
            setVisible(false);
            var view = new UserMainPanel(1200, 700);
        });

        deleteBtn.addActionListener(e -> {
            var view = new DeleteHousePanel(600, 400);
        });

        addBtn.addActionListener(e -> {
            var view = new AddHousePanel(700, 500);
        });

        add(addRemoveHousePanel);
        setVisible(true);

    }

}
