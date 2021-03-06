package lt.web.service;

import lt.web.models.Children;
import lt.web.models.Fosters;
import lt.web.repository.ChildrenRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildrenService implements IChildrenService {

    @Autowired
    ChildrenRep childrenRep;

    public ChildrenService(ChildrenRep childrenRep) {
        this.childrenRep = childrenRep;
    }

    @Override
    public List<Children> getAllChildren() {
        List<Children> childrenList = childrenRep.findAll();
        return childrenList;
    }

    @Override
    public void saveChildren(Children children) {
        childrenRep.saveChild(children.getName(), children.getSurname(), children.getFoster().getFosterId(), children.getSchoolClasses().getSchoolClassesId(), children.getUser().getUserId());
    }

    @Override
    public void updateChildren(Children children) {
        childrenRep.updateChild(children.getName(), children.getSurname(), children.getFoster().getFosterId(), children.getSchoolClasses().getSchoolClassesId(), children.getUser().getUserId(), children.getChildId());
    }

    @Override
    public void deleteChildren(int childId) {
        childrenRep.delete(Long.valueOf(childId));
    }

    @Override
    public void updateChildrensFoster(int childId, int fosterId) {
        childrenRep.updateChildrensFoster(childId, fosterId);
    }

    @Override
    public void updateChildrenByClassId(int schoolClassesId, int id) {
        childrenRep.updateChildrensClasses(schoolClassesId, id);
    }
}
