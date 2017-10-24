package lt.web.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "webroles")
public class Roles {
    int roleId;
    String roleTitle;
    List<Users> usersList;

    public Roles() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID", unique = true, nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Column(name = "ROLE_TITLE", nullable = false, length = 64)
    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
}
