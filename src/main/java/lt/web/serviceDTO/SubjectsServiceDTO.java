package lt.web.serviceDTO;

import lt.web.modelDTO.SubjectsDTO;
import lt.web.models.Subjects;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectsServiceDTO {

    public List<SubjectsDTO> convertSubjectsDTO(List<Subjects> subject) {
        List<SubjectsDTO> subjectsDTOList = new ArrayList<>();
        for(Subjects sb: subject){
            SubjectsDTO subjectsDTO = new SubjectsDTO(sb.getSubjectId(), sb.getSubjectName());
            subjectsDTOList.add(subjectsDTO);
        }
        return subjectsDTOList;
    }
}
