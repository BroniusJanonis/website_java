package lt.web.service;

import lt.web.modelDTO.TeachersDTO;
import lt.web.models.Teachers;
import lt.web.repository.TeachersRep;
import lt.web.serviceDTO.TeachersServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements ITeacherService{
    @Autowired
    private TeachersRep teachersRep;
    @Autowired
    private TeachersServiceDTO teachersServiceDTO;

    public List<TeachersDTO> getAllTeachers(){
        List<Teachers> teachersList = teachersRep.findAll();
        List<TeachersDTO> teachersDTOList = teachersServiceDTO.teacherDTOList(teachersList);
        return  teachersDTOList;
    }

    @Override
    public void saveAndFlushTeacher(Teachers teachers) {
        teachersRep.saveAndFlush(teachers);
    }

    @Override
    public void saveTeacher(Teachers teachers) {
        teachersRep.save(teachers);
    }
}
