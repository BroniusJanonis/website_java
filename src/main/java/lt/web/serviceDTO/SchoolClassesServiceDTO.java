package lt.web.serviceDTO;

import lt.web.modelDTO.ChildrenDTO;
import lt.web.modelDTO.FostersDTO;
import lt.web.modelDTO.SchoolClassesDTO;
import lt.web.models.Children;
import lt.web.models.SchoolClasses;
import lt.web.repository.SchoolClassesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolClassesServiceDTO {

    @Autowired
    private ChildrenServiceDTO childrenServiceDTO;

    public SchoolClassesDTO convertSchoolClassesDTO(SchoolClasses schoolClass) {

        List<ChildrenDTO> childrenListDTO = childrenServiceDTO.convertChildrenDTO(schoolClass.getChildrenList());

        SchoolClassesDTO schoolClassesDTO = new SchoolClassesDTO(schoolClass.getSchoolClassesId(), schoolClass.getTitle(), childrenListDTO);

        return schoolClassesDTO;
    }
}
