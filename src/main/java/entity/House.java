package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "house")
public class House {
    @Id
    private String id;

    @Column
    private String street;

    @Column
    private int no;

    @Column
    private String bl;

    @Column
    private int ap;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Request> requests;

    public House(User user, String street, int no, String bl, int ap) {
        this.user = user;
        this.street = street;
        this.no = no;
        this.bl = bl;
        this.ap = ap;
    }

    public House(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "House{" +
                "id='" + id + '\'' +
                ", street='" + street + '\'' +
                ", no=" + no +
                ", bl='" + bl + '\'' +
                ", ap=" + ap +
                ", user=" + user +
                '}';
    }

    public String prettyPrint(){
        return street + ", nr. " + no + ", " +
                "bl. " + bl + ", ap. " + ap;
    }
}
