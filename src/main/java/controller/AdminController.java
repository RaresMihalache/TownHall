package controller;

import Constants.Constants;
import entity.Request;
import entity.RequestType;
import entity.User;
import service.AdminService;
import view.LoginPanel;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class AdminController {

    private AdminService adminService;

    public AdminController(){
        adminService = new AdminService();
    }

    public void back(){
        var view = new LoginPanel(1000, 550);
    }

    // view tables -> req types & users

    public ArrayList<User> viewUsersData(){
        ArrayList<User> users = (ArrayList<User>) adminService.viewUsers();
        return users;
    }

    public ArrayList<Request> viewRequestsData(){
        ArrayList<Request> requests = (ArrayList<Request>) adminService.getAllRequests();
        return requests;
    }

    public void approveReq(String reqId){
        int validation = adminService.approveReq(reqId);
        if(validation == 1){
            JOptionPane.showMessageDialog(
                    null,
                    Constants.APPROVE_REQUEST_SUCCES,
                    "Admin | Approve request > Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        else{ // -1
            JOptionPane.showMessageDialog(
                    null,
                    Constants.REQUEST_ID_ERROR,
                    "Admin | Approve request > Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void deleteReq(String reqId){
        int validation = adminService.deleteReq(reqId);
        if(validation == 1){
            JOptionPane.showMessageDialog(
                    null,
                    Constants.DELETE_REQUEST_SUCCESS,
                    "Admin | Approve request > Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        else{ // -1
            JOptionPane.showMessageDialog(
                    null,
                    Constants.REQUEST_ID_ERROR,
                    "Admin | Approve request > Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public List<Request> filterByDate(){
        return adminService.filterByDate();
    }

    public List<Request> filterByReqType(String reqType){
        return adminService.filterByReqType(reqType);
    }

    public ArrayList<RequestType> viewReqTypesData(){
        ArrayList<RequestType> requestTypes = (ArrayList<RequestType>) adminService.viewReqTypes();
        return requestTypes;
    }

    /**
     *
     * @return ArrayList with all fields excepting the last one (houses)
     */
    public ArrayList<Object> viewUsersFields(){
        ArrayList<Object> usersArray = adminService.retrieveFields(User.class);
        ArrayList<Object> returnArray = new ArrayList<Object>();
        for(int i = 0; i < usersArray.size() - 1; i ++)
            returnArray.add(usersArray.get(i));
        return returnArray;
    }

    public ArrayList<Object> viewReqTypeFields(){
        ArrayList<Object> reqTypeArray = adminService.retrieveFields(RequestType.class);
        ArrayList<Object> returnArray = new ArrayList<>();
        for(int i = 0; i < reqTypeArray.size() - 1; i ++)
            returnArray.add(reqTypeArray.get(i));
        return returnArray;
    }

    public List<Object> viewRequestsFields(){
        ArrayList<Object> requestsArray = adminService.retrieveFields(Request.class);
        ArrayList<Object> returnArray = new ArrayList<Object>();
        for(int i = 2; i < requestsArray.size(); i ++) // ignore first 2 fields: dt and sdf
            returnArray.add(requestsArray.get(i));
        return returnArray;
    }

    public void createNewReqType(String name){
        adminService.addRequestType(name);
        JOptionPane.showMessageDialog(
                null,
                Constants.NEW_REQUEST_TYPE,
                "Admin | Add new request type > Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void deleteReqType(String id){
        int validation = adminService.deleteRequestType(id);
        if(validation == 1){
            JOptionPane.showMessageDialog(
                    null,
                    Constants.DELETE_REQUEST_TYPE_SUCCESS,
                    "Admin | Remove request type > Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        else{ // validaiton == -1
            JOptionPane.showMessageDialog(
                    null,
                    Constants.DELETE_REQUEST_TYPE_ERROR,
                    "Admin | Remove request type > Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public List<RequestType> getAllReqTypes(){
        List<RequestType> requestTypes = adminService.getAllReqTypes();
        return requestTypes;
    }

}
