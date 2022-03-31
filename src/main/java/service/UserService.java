package service;

import entity.House;
import entity.Request;
import entity.RequestType;
import entity.User;
import repository.HouseRepo;
import repository.RequestRepo;
import repository.RequestTypeRepo;
import repository.UserRepo;
import validator.GeneralValidator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserRepo userRepo;
    private HouseRepo houseRepo;
    private RequestRepo requestRepo;
    private RequestTypeRepo requestTypeRepo;

    private static String email;
    private static User user;

    public UserService(){
        userRepo = new UserRepo();
        houseRepo = new HouseRepo();
        requestRepo = new RequestRepo();
        requestTypeRepo = new RequestTypeRepo();
    }

    /**
     * returns 0 -> No User found in db
     * returns 1 -> User found in db
     * returns 2 -> Admin
     * **/
    public int login(String email, String password){
        if(email.equals("admin123@gmail.com") && password.equals("abc"))
            return 2;

        List<User> getUserList = userRepo.findByEmailAndPassword(email, password);
        return getUserList.size();
    }

    public User getLoggedUser(String email, String password){
        List<User> getUserList = userRepo.findByEmailAndPassword(email, password);
        for(User u : getUserList){
            System.out.println(u.toString());
        }
        return getUserList.get(0);
    }

    public int register(String email, String username, String password, String passwordConfirmation){
        int registerValue = GeneralValidator.registerValidation(email, username, password, passwordConfirmation);
        List<User> users = userRepo.findByEmail(email);
        if(users.size() == 0 && registerValue == 1) // success -> good data
            userRepo.insertNewUser(email, username, password);
        else if(users.size() > 0)
            return 0;
        return registerValue;
    }

    public ArrayList<Object> retrieveFields(Class object){
        ArrayList<Object> myArray = new ArrayList();
        for(Field field : object.getDeclaredFields()){
            myArray.add(field.getName());
        }
        return myArray;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public String getEmailService(){
        return email;
    }

    public List<House> getHouses(String email){
        List<House> houses = houseRepo.getHousesByUserEmail(user.getEmail());
        System.out.println(houses.size());
        return houses;
    }

    public List<Request> getRequests(String email){
        List<Request> requests = requestRepo.getRequestsByUserEmail(user.getEmail());
        return requests;
    }

    public int removeHouse(String houseId){
        int validation = houseRepo.removeHouse(houseId);
        return validation;
    }

    public int addHouse(String street, String no, String bl, String ap){
        int validation = GeneralValidator.addHouseValidation(street, no, bl, ap);
        if(validation == 1) { // success
            int noInt = Integer.parseInt(no);
            int apInt = Integer.parseInt(ap);
            houseRepo.insertNewHouse(street, noInt, bl, apInt, user);
            return 1; // successfully added new house
        }
        else if(validation == -1) return -1; // no parseInt error
        else return -2; // ap parseInt error
    }

    public ArrayList<String> parseHouseInfo(String houseInfo){
        ArrayList<String> toReturn = new ArrayList<String>();

        int[] tokensComma = new int[3];
        int k = 0;
        for(int i = 0; i < houseInfo.length(); i ++) {
            if (houseInfo.charAt(i) == ',')
                tokensComma[k++] = i + 1; // get all indexes that have ','
        }

        String street = houseInfo.substring(0, tokensComma[0] - 1);
        String noPart = houseInfo.substring(tokensComma[0] + 1, tokensComma[1]);
        String blPart = houseInfo.substring(tokensComma[1] + 1, tokensComma[2]);
        String apPart = houseInfo.substring(tokensComma[2] + 1);

        String no = noPart.substring(noPart.indexOf('.') + 2, noPart.indexOf(','));
        String bl = blPart.substring(blPart.indexOf('.') + 2, blPart.indexOf(','));
        String ap = apPart.substring(apPart.indexOf('.') + 2);

        toReturn.add(street);
        toReturn.add(no);
        toReturn.add(bl);
        toReturn.add(ap);

        return toReturn;
    }

    public List<RequestType> getAllReqTypes(){
        List<RequestType> reqTypes = requestTypeRepo.findAll();
        return  reqTypes;
    }

    public int addRequest(String reqTypeName, String details, String houseInfo){
        List<String> houseFields = parseHouseInfo(houseInfo);
        House house = houseRepo.getHouseByData(
                houseFields.get(0),
                Integer.parseInt(houseFields.get(1)),
                houseFields.get(2),
                Integer.parseInt(houseFields.get(3)));
        RequestType reqType = requestTypeRepo.findReqByName(reqTypeName);
        int validate = requestRepo.checkNoReqTypeOnHouse(house.getId(), reqType.getId());
        if(validate < 3){
            requestRepo.addRequest(reqType, details, house);
            return 1;
        }
        return - 1;
    }

    public int updateRequest(String reqId,  String reqTypeName, String details, String houseInfo){
        List<String> houseFields = parseHouseInfo(houseInfo);
        House house = houseRepo.getHouseByData(
                houseFields.get(0),
                Integer.parseInt(houseFields.get(1)),
                houseFields.get(2),
                Integer.parseInt(houseFields.get(3))
        );
        RequestType reqType = requestTypeRepo.findReqByName(reqTypeName);
        int validation = requestRepo.updateRequest(reqId, reqType, details, house);
        return validation;
    }

    /**
     *
     * @param reqId id to be delted
     * @return -1 -> id NOT FOUND
     *          1 -> id FOUND
     */
    public int deleteRequest(String reqId){
        return requestRepo.deleteRequest(reqId);
    }

}
