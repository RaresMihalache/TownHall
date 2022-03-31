package service;

import entity.Request;
import entity.RequestType;
import entity.User;
import repository.RequestRepo;
import repository.RequestTypeRepo;
import repository.UserRepo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private UserRepo userRepo;
    private RequestTypeRepo requestTypeRepo;
    private RequestRepo requestRepo;

    public AdminService(){
        userRepo = new UserRepo();
        requestTypeRepo = new RequestTypeRepo();
        requestRepo = new RequestRepo();
    }

    // for view users operation

    public List<User> viewUsers(){
        List<User> users = userRepo.findAll();
        return users;
    }

    public ArrayList<Object> retrieveFields(Class object){
        ArrayList<Object> myArray = new ArrayList();
        for(Field field : object.getDeclaredFields()){
            myArray.add(field.getName());
        }
        return myArray;
    }

    // for add/delete requestType operation

    public List<RequestType> viewReqTypes(){
        List<RequestType> requestTypes = requestTypeRepo.findAll();
        return requestTypes;
    }

    public List<Request> getAllRequests(){
        return requestRepo.getAllRequests();
    }

    public int approveReq(String reqId){
        return requestRepo.approveReq(reqId);
    }

    public int deleteReq(String reqId){
        return requestRepo.deleteRequest(reqId);
    }

    public List<Request> filterByDate(){
        return requestRepo.filterByDate();
    }

    public List<Request> filterByReqType(String reqType){
        return requestRepo.filterByReqType(reqType);
    }

    public List<RequestType> getAllReqTypes(){
        List<RequestType> reqTypes = requestTypeRepo.findAll();
        return  reqTypes;
    }

    public void addRequestType(String name){
        requestTypeRepo.createNewReqType(name);
    }

    public int deleteRequestType(String id){
        int validation = requestTypeRepo.deleteReqType(id);
        return validation;
    }


}
