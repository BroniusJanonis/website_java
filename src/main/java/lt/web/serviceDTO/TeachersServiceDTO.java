package lt.web.serviceDTO;

import lt.web.modelDTO.*;
import lt.web.models.Children;
import lt.web.models.SchoolClasses;
import lt.web.models.Teachers;
import lt.web.models.Users;
import lt.web.repository.TeachersRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeachersServiceDTO {

    @Autowired
    private UserServiceDTO userServiceDTO;
    @Autowired
    private SchoolClassesServiceDTO schoolClassesServiceDTO;

    public List<TeachersDTO> teacherDTOList(List<Teachers> teachersList) {
        List<TeachersDTO> teachersDTOList = new ArrayList<>();
        for(Teachers teach: teachersList){

            // gaunam Users teacheriu
            UsersDTO usersDTO = userServiceDTO.convertUserDto(teach);

            // gaunam Schoolclases teacheriu
            SchoolClassesDTO schoolClassesDTO = schoolClassesServiceDTO.convertSchoolClassesDTO(teach.getSchoolClasses());

            // gaunam teacher parsinta
            TeachersDTO teachersDTO = new TeachersDTO(teach.getTeacherId(), teach.getName(), teach.getSurname(), teach.getPhone(), teach.getSubject(), schoolClassesDTO, usersDTO);

            // TeachersDTO dedam i List<TeachersDTO> teachersDTOList
            teachersDTOList.add(teachersDTO);
        }
        return teachersDTOList;
    }


}
