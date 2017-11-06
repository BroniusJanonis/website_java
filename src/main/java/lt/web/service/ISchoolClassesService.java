package lt.web.service;

import lt.web.models.SchoolClasses;
import lt.web.models.Teachers;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISchoolClassesService {
    int setTitleById(@Param("schoolClassesTitle") String schoolClassesTitle, @Param("teacherId ") int teacherId , @Param("schoolClassesId") int schoolClassesId);
    SchoolClasses save(String title, Teachers teachers);
    void deleteSchoolclassesByTeacherId(int id);
    List<SchoolClasses> getSchoolChlassesList();
}
