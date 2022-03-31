package view;

import controller.AdminController;
import entity.Request;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ReturnFilterPanel extends JFrame {
    private AdminController adminController;

    private JPanel filterPanel = new JPanel();
    private JPanel tablePanel = new JPanel();
    private JButton backBtn = new JButton("\u2BA8");
    private JTable requestTable;
    private Object[] columns;
    private Object[][] data;

    private Font backBtnFont = new Font("Serif", Font.PLAIN, 55);

    // filterFlag == 1 => requestsByData
    // filterFlag == 2 => requestsByReqType
    public ReturnFilterPanel(int width, int height, int filterFlag, List<Request> requests){
        adminController = new AdminController();

        columns = adminController.viewRequestsFields().toArray();
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
        setTitle("Primaria abc | Admin Main panel > filter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        filterPanel.setLayout(null);
        filterPanel.setBounds(0, 0, width, height);
        filterPanel.setBackground(new Color(177, 218, 125));

        backBtn.setFont(backBtnFont);

        backBtn.setBounds(50, 50, 150, 75);

        tablePanel.setLayout(new GridLayout(1, 0));
        tablePanel.setBounds(100, 200, width - 200, height - 250);

        requestTable = new JTable(data, columns);
        tablePanel.add(new JScrollPane(requestTable));
        tablePanel.setVisible(true);

        filterPanel.add(backBtn);
        filterPanel.add(tablePanel);

        backBtn.addActionListener(e -> {
            setVisible(false);
            var view = new FilterPanel(1200, 700);
        });

        add(filterPanel);
        setVisible(true);
    }
}
