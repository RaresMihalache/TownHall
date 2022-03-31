package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "request_type")
public class RequestType {

    @Id
    private String id;

    @Column
    private String name;

    @OneToMany(mappedBy = "requestType", cascade = CascadeType.ALL)
    private List<Request> requests;

    public RequestType(){}

    public RequestType(String name){
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "RequestType{" +
                "id='" + id + '\'' +
                ", name='" + name +
                '}';
    }

}
