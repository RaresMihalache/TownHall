package view;

import controller.UserController;
import entity.House;
import entity.Request;
import entity.RequestType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DeleteReqPanel extends JFrame {
    private UserController userController;

    private JPanel deleteReqPanel = new JPanel();
    private JPanel tablePanel = new JPanel();
    private JButton backBtn = new JButton("\u2BA8");
    private JButton updateBtn = new JButton("Update");
    private JButton deleteBtn = new JButton("Delete");
    private JLabel idLbl1 = new JLabel("Id");
    private JLabel idLbl2 = new JLabel("Id");
    private JLabel reqTypeLbl = new JLabel("Request type");
    private JLabel detailsLbl = new JLabel("Details");
    private JLabel houseLbl = new JLabel("House");
    private JTextField idTxt1 = new JTextField();
    private JTextField idTxt2 = new JTextField();
    private JComboBox<String> reqTypeTxt = new JComboBox<String>();
    private JTextField detailsTxt = new JTextField();
    private JComboBox<String> houseTxt = new JComboBox<String>();
    private JTable requestsTable;
    private Object[] columns;
    private Object[][] data;

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);
    private Font backBtnFont = new Font("Serif", Font.PLAIN, 55);

    public DeleteReqPanel(int width, int height){
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
        }

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setTitle("Primaria abc | User Main Panel > Requests > Update/Delete");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        deleteReqPanel.setLayout(null);
        deleteReqPanel.setBounds(0, 0, width, height);
        deleteReqPanel.setBackground(new Color(177, 218, 125));

        backBtn.setFont(backBtnFont);
        deleteBtn.setFont(normalFont);
        updateBtn.setFont(normalFont);
        idLbl1.setFont(normalFont);
        idLbl2.setFont(normalFont);
        reqTypeLbl.setFont(normalFont);
        detailsLbl.setFont(normalFont);
        houseLbl.setFont(normalFont);
        idTxt1.setFont(normalFont);
        idTxt2.setFont(normalFont);
        reqTypeTxt.setFont(normalFont);
        detailsTxt.setFont(normalFont);
        houseTxt.setFont(normalFont);

        setReqTypeItems();
        setHouseInfoItems();

        backBtn.setBounds(50, 50, 150, 75);
        updateBtn.setBounds(250, 250, 500, 45);
        deleteBtn.setBounds(850, 250, 300, 45);
        idLbl1.setBounds(250, 50, 150, 45);
        idLbl2.setBounds(850, 50, 150, 45);
        reqTypeLbl.setBounds(250, 100, 150, 45);
        detailsLbl.setBounds(250, 150, 150, 45);
        houseLbl.setBounds(250, 200, 150, 45);
        idTxt1.setBounds(400, 50, 350, 45);
        reqTypeTxt.setBounds(400, 100, 350, 45);
        detailsTxt.setBounds(400, 150, 350, 45);
        houseTxt.setBounds(400, 200, 350, 45);
        idTxt2.setBounds(900, 50, 250, 45);

        tablePanel.setLayout(new GridLayout(1, 0));
        tablePanel.setBounds(100, 350, width - 200, height - 450);

        requestsTable = new JTable(data, columns);
        tablePanel.add(new JScrollPane(requestsTable));
        tablePanel.setVisible(true);

        deleteReqPanel.add(backBtn);
        deleteReqPanel.add(updateBtn);
        deleteReqPanel.add(deleteBtn);
        deleteReqPanel.add(idLbl1);
        deleteReqPanel.add(idLbl2);
        deleteReqPanel.add(reqTypeLbl);
        deleteReqPanel.add(detailsLbl);
        deleteReqPanel.add(houseLbl);
        deleteReqPanel.add(tablePanel);
        deleteReqPanel.add(idTxt1);
        deleteReqPanel.add(reqTypeTxt);
        deleteReqPanel.add(detailsTxt);
        deleteReqPanel.add(houseTxt);
        deleteReqPanel.add(idTxt2);

        backBtn.addActionListener(e -> {
            setVisible(false);
            var view = new UserCrudReqPanel(1200, 700);
        });

        updateBtn.addActionListener(e -> {
            //userController.;
            System.out.println(
                    idTxt1.getText() + "  " +
                    detailsTxt.getText() + "   " +
                    houseTxt.getItemAt(houseTxt.getSelectedIndex()).toString() + "  " +
                    reqTypeTxt.getItemAt(reqTypeTxt.getSelectedIndex()).toString()
                    );
            userController.updateRequest(
                    idTxt1.getText(),
                    reqTypeTxt.getItemAt(reqTypeTxt.getSelectedIndex()).toString(),
                    detailsTxt.getText(),
                    houseTxt.getItemAt(houseTxt.getSelectedIndex()).toString()
            );
        });

        deleteBtn.addActionListener(e -> {
            System.out.println(idTxt2.getText());
            userController.deleteRequest(idTxt2.getText());
        });

        add(deleteReqPanel);
        setVisible(true);


    }

    public ArrayList<String> getReqTypeItems(){
        ArrayList<RequestType> requestTypeList = (ArrayList<RequestType>) userController.getAllReqTypes();
        ArrayList<String> returnList = new ArrayList<String>();
        for(RequestType r : requestTypeList)
            returnList.add(r.getName());
        return returnList;
    }

    public void setReqTypeItems(){
        ArrayList<String> reqTypeItems = getReqTypeItems();
        for(String r : reqTypeItems)
            reqTypeTxt.addItem(r);
    }

    public ArrayList<String> getHouseInfoItems(){
        ArrayList<House> housesList = (ArrayList<House>) userController.viewHousesData();
        ArrayList<String> returnList = new ArrayList<String>();
        for(House h : housesList)
            returnList.add(h.prettyPrint());
        return returnList;
    }

    public void setHouseInfoItems(){
        ArrayList<String> houseInfo = getHouseInfoItems();
        for(String h : houseInfo)
            houseTxt.addItem(h);
    }
}
