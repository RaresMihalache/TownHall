package entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "request")
public class Request {

    public static Date dt = new Date();
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Id
    private String id;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private RequestType requestType;

    @Column
    private String details;

    @Column
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    @Column
    private String approveStatus;

    public Request(){}

    public Request(RequestType requestType, String details){
        this.requestType = requestType;
        this.details = details;
        this.date = sdf.format(dt);
        this.approveStatus = "no";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public int getNo_req() {
        return no_req;
    }*/

    /*public void setNo_req(int no_req) {
        this.no_req = no_req;
    }*/

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType type) {
        this.requestType = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id='" + id + '\'' +
                ", requestType='" + requestType + '\'' +
                ", date='" + date + '\'' +
                ", details='" + details + '\'' +
                ", house=" + house +
                '}';
    }
}
