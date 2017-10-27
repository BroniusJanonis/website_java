package lt.web.modelDTO;

import java.util.List;


public class RolesDTO {
    private int roleId;
    private String roleTitle;
    private List<UsersDTO> usersList;

    public RolesDTO() {
    }

    public RolesDTO(int roleId, String roleTitle) {
        this.roleId = roleId;
        this.roleTitle = roleTitle;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }
    
    public List<UsersDTO> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UsersDTO> usersList) {
        this.usersList = usersList;
    }
}
