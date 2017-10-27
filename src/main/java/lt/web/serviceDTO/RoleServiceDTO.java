package lt.web.serviceDTO;

import lt.web.modelDTO.RolesDTO;
import lt.web.models.Roles;
import lt.web.repository.RoleRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceDTO {


    public RolesDTO convertRolesDTO(Roles role) {
        RolesDTO roleDTO = new RolesDTO(role.getRoleId(), role.getRoleTitle());
        return roleDTO;
    }
}
