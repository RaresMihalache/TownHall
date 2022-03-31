package view;

import controller.UserController;
import entity.Request;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserCrudReqPanel extends JFrame {
    private UserController userController;

    private JPanel crudReqPanel = new JPanel();
    private JPanel tablePanel = new JPanel();
    private JButton backBtn = new JButton("\u2BA8");
    private JButton addBtn = new JButton("Add");
    private JButton updateBtn = new JButton("Update");
    private JButton deleteBtn = new JButton("Delete");
    private JTable requestsTable;
    private Object[] columns;
    private Object[][] data;

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);
    private Font backBtnFont = new Font("Serif", Font.PLAIN, 55);

    public UserCrudReqPanel(int width, int height){
        userController = new UserController();

        columns = userController.viewRequestsFields().toArray();
        ArrayList<Request> requests = userController.viewRequestsData();
        data = new Object[requests.size()][columns.length];
        for(int i = 0; i < requests.size(); i ++){
            data[i][0] = requests.get(i).getId();
            data[i][1] = requests.get(i).getRequestType().getName();
            data[i][2] = requests.get(i).getDetails();
            data[i][3] = requests.get(i).getDate();
            data[i][4] = requests.get(i).getHouse().prettyPrint();
            data[i][5] = requests.get(i).getApproveStatus();
        }

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setTitle("Primaria abc | User Main panel > Requests > View");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        crudReqPanel.setLayout(null);
        crudReqPanel.setBounds(0, 0, width, height);
        crudReqPanel.setBackground(new Color(177, 218, 125));

        backBtn.setFont(backBtnFont);
        addBtn.setFont(normalFont);
        updateBtn.setFont(normalFont);
        deleteBtn.setFont(normalFont);

        backBtn.setBounds(50, 50, 150, 75);
        addBtn.setBounds(350, 50, 150, 75);
        updateBtn.setBounds(650, 50, 150, 75);
        deleteBtn.setBounds(950, 50, 150, 75);

        tablePanel.setLayout(new GridLayout(1, 0));
        tablePanel.setBounds(100, 150, width - 200, height - 250);

        requestsTable = new JTable(data, columns);
        tablePanel.add(new JScrollPane(requestsTable));
        tablePanel.setVisible(true);

        crudReqPanel.add(backBtn);
        crudReqPanel.add(addBtn);
        crudReqPanel.add(updateBtn);
        crudReqPanel.add(deleteBtn);
        crudReqPanel.add(tablePanel);

        backBtn.addActionListener(e -> {
            setVisible(false);
            var view = new UserMainPanel(1200, 700);
        });

        addBtn.addActionListener(e -> {
            setVisible(false);
            var view = new AddReqPanel(1200, 700);
        });

        updateBtn.addActionListener(e -> {
            setVisible(false);
            var view = new DeleteReqPanel(1200, 800);
        });

        deleteBtn.addActionListener(e -> {
            setVisible(false);
            var view = new DeleteReqPanel(1200, 800);
        });

        add(crudReqPanel);
        setVisible(true);

    }

}
