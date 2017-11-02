package lt.web.service;

import lt.web.repository.SchoolClassesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassesService implements ISchoolClassesService {

    @Autowired
    private SchoolClassesRep schoolClassesRep;

    @Override
    public int setTitleById(String schoolClassesTitle, int teacherId, int schoolClassesId) {
        int i = schoolClassesRep.setTitleById(schoolClassesTitle, teacherId, schoolClassesId);
        return i;
    }
}