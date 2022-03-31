package view;

import controller.AdminController;
import entity.Request;
import entity.RequestType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FilterPanel extends JFrame {
    private AdminController adminController;

    private JPanel filterPanel = new JPanel();
    private JPanel tablePanel = new JPanel();
    private JButton backBtn = new JButton("\u2BA8");
    private JButton byDateBtn = new JButton("By date");
    private JButton byReqTypeBtn = new JButton("By request type");
    private JLabel filterLbl = new JLabel("Filter");
    private JComboBox<String> reqTypeTxt = new JComboBox<String>();
    private JTable requestTable;
    private Object[] columns;
    private Object[][] data;

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);
    private Font backBtnFont = new Font("Serif", Font.PLAIN, 55);

    public FilterPanel(int width, int height){
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
        setTitle("Primaria abc | Admin Main panel > filter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        filterPanel.setLayout(null);
        filterPanel.setBounds(0, 0, width, height);
        filterPanel.setBackground(new Color(177, 218, 125));

        backBtn.setFont(backBtnFont);
        byDateBtn.setFont(normalFont);
        byReqTypeBtn.setFont(normalFont);
        filterLbl.setFont(normalFont);
        reqTypeTxt.setFont(normalFont);

        backBtn.setBounds(50, 50, 150, 75);
        byDateBtn.setBounds(350, 75, 250, 75);
        byReqTypeBtn.setBounds(650, 75, 250, 75);
        filterLbl.setBounds(550, 20, 150, 45);
        reqTypeTxt.setBounds(900, 50, 250, 75);

        setReqTypeItems();

        tablePanel.setLayout(new GridLayout(1, 0));
        tablePanel.setBounds(100, 200, width - 200, height - 250);

        requestTable = new JTable(data, columns);
        tablePanel.add(new JScrollPane(requestTable));
        tablePanel.setVisible(true);

        filterPanel.add(backBtn);
        filterPanel.add(byDateBtn);
        filterPanel.add(byReqTypeBtn);
        filterPanel.add(filterLbl);
        filterPanel.add(tablePanel);
        filterPanel.add(reqTypeTxt);

        backBtn.addActionListener(e -> {
            setVisible(false);
            var view = new AdminMainPanel(1200, 700);
        });

        byDateBtn.addActionListener(e -> {
            List<Request> requestsByCriteria = adminController.filterByDate();
            setVisible(false);
            var view = new ReturnFilterPanel(1200, 700, 1, requestsByCriteria);
        });

        byReqTypeBtn.addActionListener(e -> {
            List<Request> requestsByCriteria = adminController.filterByReqType(
                    reqTypeTxt.getItemAt(reqTypeTxt.getSelectedIndex()));
            setVisible(false);
            var view = new ReturnFilterPanel(1200, 700, 2, requestsByCriteria);
        });

        add(filterPanel);
        setVisible(true);

    }

    public ArrayList<String> getReqTypeItems(){
        ArrayList<RequestType> requestTypeList = (ArrayList<RequestType>) adminController.getAllReqTypes();
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

}
