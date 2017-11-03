package lt.web.models;

import javax.persistence.*;

@Entity
@Table(name = "webusers")
public class Users {
    private int userId;
    private String email;
    private String password;
    private String password_auth;
    private Roles role;

    public Users() {
    }

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Users(String email, String password, Roles role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Users(int userId) {
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true, nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "EMAIL", unique = true, nullable = false, length = 64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PASSWORD", unique = true, nullable = false, length = 64)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // tai reiskia, kad neis i lentele, taciau turesim si,kaip objekta
    @Transient
    public String getPassword_auth() {
        return password_auth;
    }

    public void setPassword_auth(String password_auth) {
        this.password_auth = password_auth;
    }

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

}
