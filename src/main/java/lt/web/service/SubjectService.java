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

    @Override
    public void updateSubjectByTeacherId(int subjectId, String subjectName) {
        subjectsRep.saveAndFlush(new Subjects(subjectId, subjectName));
    }

    @Override
    public Subjects findBySubjectId(int subjectId) {
        return subjectsRep.findBySubjectId(subjectId);
    }

    @Override
    public Subjects findSubjectsByTeacher_TeacherIdAndSubjectName(int teacherId, String subjectName) {
        return subjectsRep.findSubjectsByTeacher_TeacherIdAndSubjectName(teacherId, subjectName);
    }

    @Override
    public void deleteSubjectsBySubjectName(String subjectName) {
        subjectsRep.deleteSubjectsBySubjectName(subjectName);
    }

    @Override
    public List<Subjects> findBySubjectName(String subjectName) {
        return subjectsRep.findBySubjectName(subjectName);
    }

    @Override
    public void saveSubjectWithSubjectNameOnly(String subjectName) {
        subjectsRep.save(new Subjects(subjectName));
    }

    @Override
    public void deleteSubjectsBySubjectId(int subjectId) {
        subjectsRep.delete(new Subjects(subjectId));
    }

    @Override
    public List<Subjects> findSubjectsByTeacherId(int teacherId) {
        return subjectsRep.findSubjectsByTeacher_TeacherId(teacherId);
    }

    @Override
    public void deleteSubjectsByTeacherId(int teacherId) {
        subjectsRep.deleteSubjectsByTeacher_TeacherId(teacherId);
    }

}
