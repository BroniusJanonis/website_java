package lt.web.serviceDTO;

import lt.web.modelDTO.*;
import lt.web.models.*;
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
    @Autowired
    private SubjectsServiceDTO subjectsServiceDTO;

    public List<TeachersDTO> teacherDTOList(List<Teachers> teachersList) {
        List<TeachersDTO> teachersDTOList = new ArrayList<>();
        for(Teachers teach: teachersList){

            // gaunam Users teacheriu
            UsersDTO usersDTO = userServiceDTO.convertUserDto(teach);

            // gaunam Schoolclases teacheriu
            SchoolClassesDTO schoolClassesDTO = schoolClassesServiceDTO.convertSchoolClassesDTO(teach.getSchoolClasses());

            // gaunam Subjects teacheriu
            List<SubjectsDTO> subjectsDTO = subjectsServiceDTO.convertSubjectsDTO(teach.getSubject());

            // gaunam teacher parsinta
            TeachersDTO teachersDTO = new TeachersDTO(teach.getTeacherId(), teach.getName(), teach.getSurname(), teach.getPhone(), subjectsDTO, schoolClassesDTO, usersDTO);

            // TeachersDTO dedam i List<TeachersDTO> teachersDTOList
            teachersDTOList.add(teachersDTO);
        }
        return teachersDTOList;
    }


}
