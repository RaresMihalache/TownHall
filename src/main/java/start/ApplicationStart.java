package start;

import entity.Request;
import entity.RequestType;
import entity.User;
import repository.HouseRepo;
import repository.UserRepo;
import view.LoginPanel;

import java.util.UUID;

public class ApplicationStart {

    public static void main(String[] args) {
        //ApplicationStart app = new ApplicationStart();
        //app.initData();

        var view = new LoginPanel(1000, 550);

    }

    public void initData(){
        UserRepo ur = new UserRepo();
        HouseRepo hr = new HouseRepo();

        User user1 = new User();
        user1.setId(UUID.randomUUID().toString());
        user1.setUsername("andrei.postolache");
        user1.setEmail("andyposto@gmail.com");
        user1.setPassword("andy123.");

        User[] users = new User[20];
        for(int i = 0 ; i < 20; i ++) {
            users[i] = new User();
            users[i].setId(UUID.randomUUID().toString());
        }

        users[0].setUsername("mihai_grigore");
        users[0].setEmail("mihaiGrig000@yahoo.com");
        users[0].setPassword("mihai");

        users[1].setUsername("george");
        users[1].setEmail("george.popescu@gmail.com");
        users[1].setPassword("georgica123");

        users[2].setUsername("catalin");
        users[2].setEmail("cata123@gmail.com");
        users[2].setPassword("parolamea");

        users[3].setUsername("buzoianu_grigore");
        users[3].setEmail("grig_buzz@yahoo.com");
        users[3].setPassword("buzoianu123.");

        users[4].setUsername("Bandici Andrei");
        users[4].setEmail("bandiciandrei@gmail.com");
        users[4].setPassword("banda35");

        users[5].setUsername("Laura Vasilescu");
        users[5].setEmail("lauraVas@yahoo.com");
        users[5].setPassword("lauraParola5");

        users[6].setUsername("constantin_poenaru");
        users[6].setEmail("poenCstin@gmail.com");
        users[6].setPassword("parolaSigura");

        users[7].setUsername("moldovan alex");
        users[7].setEmail("moldalex322@gmail.com");
        users[7].setPassword("moldo.alex");

        users[8].setUsername("mihaela_voinea");
        users[8].setEmail("miha32@gmail.com");
        users[8].setPassword("portal");

        users[9].setUsername("voiculescu adrian");
        users[9].setEmail("adi1976@yahoo.com");
        users[9].setPassword("jupiter56.");

        users[10].setUsername("test1");
        users[10].setEmail("test1@gmail.com");
        users[10].setPassword("test1");

        users[11].setUsername("test2");
        users[11].setEmail("test2@gmail.com");
        users[11].setPassword("test2");

        users[12].setUsername("bucur.cristian");
        users[12].setEmail("cristianbucur058@gmail.com");
        users[12].setPassword("oSimplaParola");

        users[13].setUsername("mihnea_crisan");
        users[13].setEmail("chris.mihnea@gmail.com");
        users[13].setPassword("idkNumeParola");

        users[14].setUsername("georgian_laur");
        users[14].setEmail("laurGeorge@yahoo.com");
        users[14].setPassword("laur_georg12.");

        users[15].setUsername("maria ilinca");
        users[15].setEmail("ilimar@gmail.com");
        users[15].setPassword("marlinca");

        users[16].setUsername("andrei morar");
        users[16].setEmail("mondrei123@gmail.com");
        users[16].setPassword("mondrei");

        users[17].setUsername("alex123");
        users[17].setEmail("alexandru.paul@gmail.com");
        users[17].setPassword("palexul573");

        users[18].setUsername("user5783");
        users[18].setEmail("iustin_andrei@gmail.com");
        users[18].setPassword("parolaUnica");

        users[19].setUsername("ana maria");
        users[19].setEmail("annaMary322@gmail.com");
        users[19].setPassword("qwertyuiop123");

        for(int i = 0; i < 20; i ++)
            ur.insertNewUser(users[i]);

        hr.insertNewHouse("str. Aleea Viforului", 12, "A2", 20, users[10]);
        hr.insertNewHouse("str. Stefan cel Mare", 32, "-", 0, users[10]);
        hr.insertNewHouse("str. Ciresilor", 2, "-", 0, users[10]);
        hr.insertNewHouse("str. Ceahlau", 50, "B1", 2, users[10]);
        hr.insertNewHouse("str. Zorilor", 3, "A4", 34, users[11]);
        hr.insertNewHouse("str. Campiilor", 4, "A1", 15, users[1]);
        hr.insertNewHouse("str. Principala", 3, "-", 0, users[3]);
        hr.insertNewHouse("str. Mieilor", 7, "B3", 7, users[5]);
        hr.insertNewHouse("str. Gheorghe Doja", 132, "C4", 42, users[4]);
        hr.insertNewHouse("str. Avram Iancu", 5, "-", 0, users[11]);
        hr.insertNewHouse("str. Libertatii", 57, "A2", 24, users[0]);
        hr.insertNewHouse("str. Trandafirilor", 5, "C1", 27, users[16]);
        hr.insertNewHouse("str. Mihail Kogalniceanu", 38, "A1", 1, users[17]);
        hr.insertNewHouse("str. Stejarului", 39, "-", 0, users[5]);
        hr.insertNewHouse("str. Izvorului", 23, "B3", 19, users[17]);

        RequestType requestType1 = new RequestType("cerere generala");
        Request request1 = new Request(requestType1, "abc");
        System.out.println(request1.toString());

    }
}
