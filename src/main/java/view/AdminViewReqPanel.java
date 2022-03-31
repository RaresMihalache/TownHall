package view;

import controller.AdminController;
import entity.Request;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminViewReqPanel extends JFrame {
    private AdminController adminController;

    private JPanel crudReqPanel = new JPanel();
    private JPanel tablePanel = new JPanel();
    private JButton backBtn = new JButton("\u2BA8");
    private JButton approveBtn = new JButton("Approve");
    private JButton deleteBtn = new JButton("Delete");
    private JLabel idLbl = new JLabel("Id");
    private JTextField idTxt = new JTextField();
    private JTable requestTable;
    private Object[] columns;
    private Object[][] data;

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);
    private Font backBtnFont = new Font("Serif", Font.PLAIN, 55);

    public AdminViewReqPanel(int width, int height){
        adminController = new AdminController();

        columns = adminController.viewRequestsFields().toArray();
        ArrayList<Request> requests = adminController.viewRequestsData();
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
        setTitle("Primaria abc | Admin Main panel > Requests");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        crudReqPanel.setLayout(null);
        crudReqPanel.setBounds(0, 0, width, height);
        crudReqPanel.setBackground(new Color(177, 218, 125));

        backBtn.setFont(backBtnFont);
        approveBtn.setFont(normalFont);
        deleteBtn.setFont(normalFont);
        idLbl.setFont(normalFont);
        idTxt.setFont(normalFont);

        backBtn.setBounds(50, 50, 150, 75);
        approveBtn.setBounds(950, 250, 205, 75);
        deleteBtn.setBounds(950, 350, 205, 75);
        idLbl.setBounds(300, 75, 150, 45);
        idTxt.setBounds(370, 75, 500, 45);

        tablePanel.setLayout(new GridLayout(1, 0));
        tablePanel.setBounds(100, 150, width - 400, height - 250);

        requestTable = new JTable(data, columns);
        tablePanel.add(new JScrollPane(requestTable));
        tablePanel.setVisible(true);

        crudReqPanel.add(backBtn);
        crudReqPanel.add(approveBtn);
        crudReqPanel.add(deleteBtn);
        crudReqPanel.add(idLbl);
        crudReqPanel.add(idTxt);
        crudReqPanel.add(tablePanel);

        backBtn.addActionListener(e -> {
            setVisible(false);
            var view = new AdminMainPanel(1200, 700);
        });

        approveBtn.addActionListener(e -> {
            adminController.approveReq(idTxt.getText());
        });

        deleteBtn.addActionListener(e -> {
            adminController.deleteReq(idTxt.getText());
        });

        add(crudReqPanel);
        setVisible(true);

    }
}
