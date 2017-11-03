package lt.web.service;

import lt.web.models.Subjects;
import lt.web.models.Teachers;
import lt.web.models.Users;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISubjectService {
    int setSubjectById(@Param("subjectName") String subjectName, @Param("teacherId") int teacherId , @Param("subjectId") int subjectId);
    List<Subjects> findAll();
    Subjects save(String subjectName, Teachers teacher);
}
