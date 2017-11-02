package lt.web.service;

import lt.web.models.Subjects;
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
}
