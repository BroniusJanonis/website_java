package lt.web.service;

import lt.web.models.Subjects;
import lt.web.models.Teachers;
import lt.web.repository.SubjectsRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService implements ISubjectService{

    @Autowired
    SubjectsRep subjectsRep;
    @Override
    public int setSubjectById(String subjectName, int teacherId, int subjectId) {
        int i = subjectsRep.setSubjectById(subjectName, teacherId, subjectId);
        return i;
    }

    @Override
    public List<Subjects> findAll() {
        List<Subjects> subjectsList = subjectsRep.findAll();
        return subjectsList;
    }

    @Override
    public Subjects save(String subjectName, Teachers teacher) {
        Subjects subjects = subjectsRep.saveAndFlush(new Subjects(subjectName, teacher));
        return subjects;
    }

    @Override
    public List<Integer> getTeachersListBySubjectName(String subjectName) {
        return subjectsRep.getTeachersListBySubjectName(subjectName);
    }

}
