package lt.web.serviceDTO;

import lt.web.modelDTO.ChildrenDTO;
import lt.web.modelDTO.FostersDTO;
import lt.web.models.Children;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildrenServiceDTO {

    @Autowired
    private FostersServiceDTO fostersServiceDTO;

    public List<ChildrenDTO> convertChildrenDTO(List<Children> childrenList) {


        List<ChildrenDTO> childrenDTOList = new ArrayList<>();
        for(Children children: childrenList){
            FostersDTO fostersDTO = fostersServiceDTO.convertFostersDTO(children.getFoster());
            ChildrenDTO childrenDTO = new ChildrenDTO(children.getChildId(), children.getName(), children.getSurname(), fostersDTO);
            childrenDTOList.add(childrenDTO);
        }

        return  childrenDTOList;
    }
}
