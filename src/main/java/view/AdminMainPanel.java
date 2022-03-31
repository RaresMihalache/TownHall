package view;

import controller.AdminController;

import javax.swing.*;
import java.awt.*;

public class AdminMainPanel extends JFrame {

    private JPanel adminPanel = new JPanel();
    private JButton backBtn = new JButton("\u2BA8");
    private JButton viewUsersBtn = new JButton("<html>View<br>users</html>");
    private JButton addDeleteDocTypesBtn = new JButton("<html><p style = text-align:center>Add /<br>delete<br>document<br>types</p></html>");
    private JButton approveDeleteReqBtn = new JButton("<html>Approve /<br>delete<br>requests</html>");
    private JButton filterBtn = new JButton("<html>Filter<br>requests</html>");
    private JLabel versionLbl = new JLabel("Version 1.0.0.");

    private Font normalFont = new Font("Serif", Font.PLAIN, 25);
    private Font mainBtnFont = new Font("Serif", Font.PLAIN, 30);
    private Font backBtnFont = new Font("Serif", Font.PLAIN, 55);

    private AdminController adminController;

    public AdminMainPanel(int width, int height){
        adminController = new AdminController();

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setTitle("Primaria abc | Admin Main panel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        adminPanel.setLayout(null);
        adminPanel.setBounds(0, 0, width, height);
        adminPanel.setBackground(new Color(177, 218, 125));

        versionLbl.setFont(normalFont);
        backBtn.setFont(backBtnFont);
        viewUsersBtn.setFont(mainBtnFont);
        addDeleteDocTypesBtn.setFont(mainBtnFont);
        approveDeleteReqBtn.setFont(mainBtnFont);
        filterBtn.setFont(mainBtnFont);
        backBtn.setBackground(new Color(100, 100, 100));

        backBtn.setBounds(50, 50, 150, 75);
        viewUsersBtn.setBounds(50, 200, 180, 300);
        addDeleteDocTypesBtn.setBounds(350, 200, 180, 300);
        approveDeleteReqBtn.setBounds(650, 200, 180, 300);
        filterBtn.setBounds(950, 200, 180, 300);
        versionLbl.setBounds(1040, 600, 150, 100);

        adminPanel.add(backBtn);
        adminPanel.add(viewUsersBtn);
        adminPanel.add(addDeleteDocTypesBtn);
        adminPanel.add(approveDeleteReqBtn);
        adminPanel.add(filterBtn);
        adminPanel.add(versionLbl);

        backBtn.addActionListener(e -> {
            adminController.back();
            setVisible(false);
        });

        viewUsersBtn.addActionListener(e -> {
            setVisible(false);
            var view = new ViewUsersPanel(1200, 700);
            adminController.viewUsersData();

        });

        addDeleteDocTypesBtn.addActionListener(e -> {
            setVisible(false);
            var view = new RequestTypePanel(1200, 700);
        });

        approveDeleteReqBtn.addActionListener(e -> {
            setVisible(false);
            var view = new AdminViewReqPanel(1200, 700);
        });

        filterBtn.addActionListener(e -> {
            setVisible(false);
            var view = new FilterPanel(1200, 700);
        });

        add(adminPanel);
        setVisible(true);
    }

}
