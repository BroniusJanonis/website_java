package lt.web.service;

import lt.web.models.Subjects;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISubjectService {
    int setSubjectById(@Param("subjectName") String subjectName, @Param("teacherId") int teacherId , @Param("subjectId") int subjectId);
    List<Subjects> findAll();
}
