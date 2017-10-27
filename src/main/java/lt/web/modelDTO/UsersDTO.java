package lt.web.modelDTO;

import javax.persistence.*;


public class UsersDTO {
    private int userId;
    private String email;
    private String password;
    private String password_auth;
    private RolesDTO roleDTO;

    public UsersDTO() {
    }

    public UsersDTO(int userId, String email, String password, RolesDTO roleDTO) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.roleDTO = roleDTO;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_auth() {
        return password_auth;
    }

    public void setPassword_auth(String password_auth) {
        this.password_auth = password_auth;
    }

    public RolesDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RolesDTO roleDTO) {
        this.roleDTO = roleDTO;
    }
}
