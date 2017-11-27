package lt.web.service;

import lt.web.models.SchoolClasses;
import lt.web.models.Teachers;
import lt.web.repository.SchoolClassesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolClassesService implements ISchoolClassesService {

    @Autowired
    private SchoolClassesRep schoolClassesRep;

    public SchoolClassesService(SchoolClassesRep schoolClassesRep) {
        this.schoolClassesRep = schoolClassesRep;
    }

    @Override
    public int setTitleById(String schoolClassesTitle, int teacherId, int schoolClassesId) {
        int i = schoolClassesRep.setTitleById(schoolClassesTitle, teacherId, schoolClassesId);
        return i;
    }

    @Override
    public SchoolClasses save(String title, Teachers teachers) {
       return schoolClassesRep.saveAndFlush(new SchoolClasses(title, teachers));
    }

    @Override
    public void deleteSchoolclassesByTeacherId(int id) {
        schoolClassesRep.deleteSchoolClassesByTeacher_TeacherId(id);
    }

    @Override
    public List<SchoolClasses> getSchoolChlassesList() {
        return schoolClassesRep.findAll();
    }

    @Override
    public SchoolClasses updateSchoolClass(SchoolClasses schoolClasses) {
        return schoolClassesRep.saveAndFlush(schoolClasses);
    }

    @Override
    public SchoolClasses findSchoolClassByTeacherId(int teacherId) {
        return schoolClassesRep.findFirstByTeacher_TeacherId(teacherId);
    }

    @Override
    public SchoolClasses saveClassesTitle(String title) {
        SchoolClasses schoolClasses = new SchoolClasses(title);
        return schoolClassesRep.saveAndFlush(schoolClasses);
    }

    @Override
    public void deleteSchoolclassesByClassId(int classId) {
        schoolClassesRep.delete(new SchoolClasses(classId));
    }

//    @Override
//    public void setTeacherIdToNullWhereSchoolClassesIdIs(int schoolClassesId) {
//        schoolClassesRep.setTeacherIdToNullWhereSchoolClassesIdIs(schoolClassesId);
//    }

    @Override
    public void updateSchoolClassByTeacherIdAndSchoolClassId(int teacherId, int schoolClassId) {
        schoolClassesRep.updateSchoolClassByTeacherId(teacherId, schoolClassId);
    }

    @Override
    public SchoolClasses findSchoolClassByschoolClassesId(int schoolClassesId) {
        return schoolClassesRep.findFirstBySchoolClassesId(schoolClassesId);
    }

}