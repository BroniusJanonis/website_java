package lt.web.service;

import org.springframework.data.repository.query.Param;

public interface ISchoolClassesService {
    int setTitleById(@Param("schoolClassesTitle") String schoolClassesTitle, @Param("teacherId ") int teacherId , @Param("schoolClassesId") int schoolClassesId);
}
