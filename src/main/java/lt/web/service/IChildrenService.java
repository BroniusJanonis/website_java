package lt.web.service;

import lt.web.models.Children;

import java.util.List;

public interface IChildrenService {
    List<Children> getAllChildren();
    void saveChildren(Children children);
    void updateChildren(Children children);
    void deleteChildren(int childId);
    void updateChildrensFoster(int childId, int fosterId);
    void updateChildrenByClassId(int schoolClassesId, int id);
}
