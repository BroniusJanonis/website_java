package lt.web.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "webfosters")
public class Fosters {
    private int fosterId;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private List<Children> childrenList;
    private Users user;

    public Fosters() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOSTER_ID", unique = true, nullable = false)
    public int getFosterId() {
        return fosterId;
    }

    public void setFosterId(int fosterId) {
        this.fosterId = fosterId;
    }

    @Column(name = "NAME", nullable = false, length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SURNAME",  nullable = false, length = 64)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "PHONE", nullable = false, length = 64)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "ADDRESS", nullable = false, length = 64)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "foster")
    public List<Children> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Children> childrenList) {
        this.childrenList = childrenList;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
