package lt.web.service;

import lt.web.modelDTO.TeachersDTO;
import lt.web.models.Teachers;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ITeacherService {
    List<TeachersDTO> getAllTeachers();
    Teachers saveAndFlushTeacher(Teachers teachers);
    Teachers saveTeacher(Teachers teachers);
    void deleteTeacher(int teacherId);
}
