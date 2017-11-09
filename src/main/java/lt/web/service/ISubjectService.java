package lt.web.service;

import lt.web.models.Subjects;
import lt.web.models.Teachers;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISubjectService {
    int setSubjectById(@Param("subjectName") String subjectName, @Param("teacherId") int teacherId , @Param("subjectId") int subjectId);
    List<Subjects> findAll();
    Subjects save(String subjectName, Teachers teacher);
    List<Integer> getTeachersListBySubjectName(String subjectName);
    void updateSubjectByTeacherId(int subjectId, String subjectName);
    Subjects findBySubjectId(@Param("subjectId") int subjectId);
    Subjects findSubjectsByTeacher_TeacherIdAndSubjectName(@Param("teacherId") int teacherId, @Param("subjectName") String subjectName);
    void deleteSubjectsBySubjectName(@Param("subjectName") String subjectName);
    List<Subjects> findBySubjectName(String subjectName);
    void saveSubjectWithSubjectNameOnly(String subjectName);
    void deleteSubjectsBySubjectId(int subjectId);
}
