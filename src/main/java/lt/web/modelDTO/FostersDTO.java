package lt.web.modelDTO;

import java.util.List;

public class FostersDTO {
    private int fosterId;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private List<ChildrenDTO> childrenList;
    private UsersDTO user;

    public FostersDTO() {
    }

    public FostersDTO(int fosterId, String name, String surname, String phone, String address) {
        this.fosterId = fosterId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public int getFosterId() {
        return fosterId;
    }

    public void setFosterId(int fosterId) {
        this.fosterId = fosterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ChildrenDTO> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<ChildrenDTO> childrenList) {
        this.childrenList = childrenList;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }
}
