package lt.web.serviceDTO;

import lt.web.modelDTO.RolesDTO;
import lt.web.modelDTO.UsersDTO;
import lt.web.models.Teachers;
import lt.web.models.Users;
import lt.web.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDTO {

    @Autowired
    private RoleServiceDTO roleServiceDTO;

    public UsersDTO convertUserDto(Teachers teach) {

        Users user = teach.getUser();
        RolesDTO rolesDTO = roleServiceDTO.convertRolesDTO(user.getRole());
        UsersDTO userDTO = new UsersDTO(user.getUserId(), user.getEmail(), user.getPassword(), rolesDTO);

        return userDTO;
    }
}
