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
}