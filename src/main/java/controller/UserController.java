package controller;

import Constants.Constants;
import entity.House;
import entity.Request;
import entity.RequestType;
import service.UserService;
import view.LoginPanel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class UserController {

    private UserService userService;

    public UserController(){
        userService = new UserService();
    }

    public void back(){
        var view = new LoginPanel(1000, 550);
    }

    public void addRemoveHouse(){
        userService.getHouses(userService.getEmailService());
    }

    public void removeHouse(String id){
        int removeHouseValid = userService.removeHouse(id);
        if(removeHouseValid == 1) {
            JOptionPane.showMessageDialog(
                    null,
                    Constants.DELETE_HOUSE_SUCCESS,
                    "User | Delete house > Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        else { // removeHouseValid == -1
            JOptionPane.showMessageDialog(
                    null,
                    Constants.DELETE_HOUSE_ERROR,
                    "User | Delete house > Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void addHouse(String street, String no, String bl, String ap){
        int addHouseValid = userService.addHouse(street, no, bl, ap);
        if(addHouseValid == 1) {
            JOptionPane.showMessageDialog(
                    null,
                    Constants.NEW_HOUSE_ADDED,
                    "User | Add new house > Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        else if(addHouseValid == -1) {
            JOptionPane.showMessageDialog(
                    null,
                    Constants.NO_PARSE_ERROR,
                    "User | Add new house > Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        else { //addHouseValid == -2
            JOptionPane.showMessageDialog(
                    null,
                    Constants.AP_PARSE_ERROR,
                    "User | Add new house > Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public ArrayList<House> viewHousesData(){
        ArrayList<House> houses = (ArrayList<House>) userService.getHouses(userService.getEmailService());
        return houses;
    }

    public List<Object> viewHousesFields(){
        ArrayList<Object> housesArray = userService.retrieveFields(House.class);
        ArrayList<Object> returnArray = new ArrayList<Object>();
        for(int i = 0; i < housesArray.size() - 1; i ++)
            returnArray.add(housesArray.get(i));
        return returnArray;
    }

    public ArrayList<Request> viewRequestsData(){
        ArrayList<Request> requests = (ArrayList<Request>) userService.getRequests(userService.getUser().getEmail());
        return requests;
    }

    public List<Object> viewRequestsFields(){
        ArrayList<Object> requestsArray = userService.retrieveFields(Request.class);
        ArrayList<Object> returnArray = new ArrayList<Object>();
        for(int i = 2; i < requestsArray.size(); i ++) // ignore first 2 fields: dt and sdf
            returnArray.add(requestsArray.get(i));
        return returnArray;
    }

    public void addRequest(String reqType, String details, String houseInfo){
        int noTypeReq = userService.addRequest(reqType, details, houseInfo);
        if(noTypeReq == 1) {
            JOptionPane.showMessageDialog(
                    null,
                    Constants.ADD_REQUEST_SUCCESS,
                    "User | Add new request > Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        else { // -1
            JOptionPane.showMessageDialog(
                    null,
                    Constants.MAX_REQUEST_TYPE,
                    "User | Add new request > Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void updateRequest(String reqId, String reqTypeName, String details, String houseInfo){
        int validation = userService.updateRequest(reqId, reqTypeName, details, houseInfo);
        if(validation == 1){
            JOptionPane.showMessageDialog(
                    null,
                    Constants.UPDATE_REQUEST_SUCCESS,
                    "User | Add new request > Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        else{ // -1
            JOptionPane.showMessageDialog(
                    null,
                    Constants.REQUEST_ID_ERROR,
                    "User | Add new request > Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    public void deleteRequest(String reqId){
        int validation = userService.deleteRequest(reqId);
        if(validation == 1){
            JOptionPane.showMessageDialog(
                    null,
                    Constants.DELETE_REQUEST_SUCCESS,
                    "User | Add new request > Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        else{ // -1
            JOptionPane.showMessageDialog(
                    null,
                    Constants.REQUEST_ID_ERROR,
                    "User | Add new request > Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public List<RequestType> getAllReqTypes(){
        List<RequestType> requestTypes = userService.getAllReqTypes();
        return requestTypes;
    }

}
