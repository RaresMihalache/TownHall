package view;

import controller.AdminController;
import entity.RequestType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RequestTypePanel extends JFrame {
    private AdminController adminController;

    private JPanel reqTypePanel = new JPanel();
    private JPanel tablePanel = new JPanel();
    private JButton backBtn = new JButton("\u2BA8");
    private JButton addBtn = new JButton("Add");
    private JButton deleteBtn = new JButton("Delete");
    private JLabel newReqNameLbl = new JLabel("<html><font color = #005700>New Request type name</html>");
    private JLabel idLbl = new JLabel("<html><font color = #ff0000>Id</html>");
    private JTextField newReqNameTxt = new JTextField();
    private JTextField idTxt = new JTextField();
    private JTable reqTypesTable;
    private Object[] columns;
    private Object[][] data;

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);
    private Font backBtnFont = new Font("Serif", Font.PLAIN, 55);

    public RequestTypePanel(int width, int height){
        adminController = new AdminController();

        columns = adminController.viewReqTypeFields().toArray();
        ArrayList<RequestType> reqTypes = adminController.viewReqTypesData();
        data = new Object[reqTypes.size()][columns.length];
        for(int i = 0; i < reqTypes.size(); i ++){
            data[i][0] = reqTypes.get(i).getId();
            data[i][1] = reqTypes.get(i).getName();
        }

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setTitle("Primaria abc | Admin Main panel > Add/Remove request type");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        reqTypePanel.setLayout(null);
        reqTypePanel.setBounds(0, 0, width, height);
        reqTypePanel.setBackground(new Color(177, 218, 125));

        backBtn.setFont(backBtnFont);
        addBtn.setFont(normalFont);
        deleteBtn.setFont(normalFont);
        newReqNameLbl.setFont(normalFont);
        idLbl.setFont(normalFont);
        newReqNameTxt.setFont(normalFont);
        idTxt.setFont(normalFont);

        backBtn.setBounds(50, 50, 150, 75);
        newReqNameLbl.setBounds(300, 50, 300, 50);
        newReqNameTxt.setBounds(250, 100, 350, 40);
        idLbl.setBounds(900, 50, 100, 50);
        idTxt.setBounds(750, 100, 350, 40);
        addBtn.setBounds(250, 155, 350, 45);
        deleteBtn.setBounds(750, 155, 350, 45);

        tablePanel.setLayout(new GridLayout(1, 0));
        tablePanel.setBounds(200, 250, width - 400, height - 350);

        reqTypesTable = new JTable(data, columns);
        tablePanel.add(new JScrollPane(reqTypesTable));
        tablePanel.setVisible(true);

        reqTypePanel.add(backBtn);
        reqTypePanel.add(tablePanel);
        reqTypePanel.add(newReqNameLbl);
        reqTypePanel.add(idLbl);
        reqTypePanel.add(newReqNameTxt);
        reqTypePanel.add(idTxt);
        reqTypePanel.add(addBtn);
        reqTypePanel.add(deleteBtn);

        backBtn.addActionListener(e -> {
            setVisible(false);
            var view = new AdminMainPanel(1200, 700);
        });
        addBtn.addActionListener(e -> {
            adminController.createNewReqType(newReqNameTxt.getText());
        });
        deleteBtn.addActionListener(e -> {
            adminController.deleteReqType(idTxt.getText());
        });

        add(reqTypePanel);
        setVisible(true);
    }
}
