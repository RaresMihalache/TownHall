package view;

import controller.UserController;
import entity.House;
import entity.RequestType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddReqPanel extends JFrame {
    private UserController userController;

    private JPanel addReqPanel = new JPanel();
    private JButton backBtn = new JButton("\u2BA8");
    private JButton addBtn = new JButton("Add");
    private JLabel reqTypeLbl = new JLabel("Request Type");
    private JLabel detailsLbl = new JLabel("Details");
    private JLabel houseLbl = new JLabel("House");
    private JComboBox<String> reqTypeTxt = new JComboBox<String>();
    private JComboBox<String> houseTxt = new JComboBox<String>();
    private JTextField detailsTxt = new JTextField();

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);
    private Font backBtnFont = new Font("Serif", Font.PLAIN, 55);

    public AddReqPanel(int width, int height){
        userController = new UserController();

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setTitle("Add request");

        addReqPanel.setLayout(null);
        addReqPanel.setBounds(0, 0, width, height);
        addReqPanel.setBackground(new Color(177, 218, 125));

        backBtn.setFont(backBtnFont);
        addBtn.setFont(normalFont);
        reqTypeLbl.setFont(normalFont);
        detailsLbl.setFont(normalFont);
        houseLbl.setFont(normalFont);
        reqTypeTxt.setFont(normalFont);
        detailsTxt.setFont(normalFont);
        houseTxt.setFont(normalFont);

        setReqTypeItems();
        setHouseInfoItems();

        backBtn.setBounds(50, 50, 150, 75);
        addBtn.setBounds(450, 550, 150, 45);
        reqTypeLbl.setBounds(450, 150, 150, 45);
        detailsLbl.setBounds(450, 250, 150, 45);
        houseLbl.setBounds(450, 350, 150, 45);
        reqTypeTxt.setBounds(650, 150, 450, 45);
        detailsTxt.setBounds(650, 250, 450, 45);
        houseTxt.setBounds(650, 350, 450, 45);

        addReqPanel.add(backBtn);
        addReqPanel.add(addBtn);
        addReqPanel.add(reqTypeLbl);
        addReqPanel.add(detailsLbl);
        addReqPanel.add(houseLbl);
        addReqPanel.add(reqTypeTxt);
        addReqPanel.add(detailsTxt);
        addReqPanel.add(houseTxt);

        backBtn.addActionListener(e -> {
            setVisible(false);
            var view = new UserCrudReqPanel(1200, 700);
        });

        addBtn.addActionListener(e -> {
            userController.addRequest(
                    reqTypeTxt.getItemAt(reqTypeTxt.getSelectedIndex()).toString(),
                    detailsTxt.getText(),
                    houseTxt.getItemAt(houseTxt.getSelectedIndex()).toString());
        });

        add(addReqPanel);
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
