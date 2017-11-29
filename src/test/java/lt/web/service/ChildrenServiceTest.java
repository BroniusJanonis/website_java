package lt.web.service;

import lt.web.models.Children;
import lt.web.models.Fosters;
import lt.web.models.SchoolClasses;
import lt.web.repository.ChildrenRep;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChildrenServiceTest {

    ChildrenService childrenService;

    @Mock
    ChildrenRep childrenRep;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        childrenService = new ChildrenService(childrenRep);
    }

    @Test
    public void getAllChildren() throws Exception {
        List<Children> childrenList = new ArrayList<>();
        childrenList.add(new Children());
        when(childrenRep.findAll()).thenReturn(childrenList);
        assertEquals(childrenService.getAllChildren(), childrenList);
    }

    @Test
    public void saveChildren() throws Exception {
        childrenRep.saveChild("name", "surname", 1, 1, 1);
        verify(childrenRep, times(1)).saveChild("name", "surname", 1, 1, 1);
    }

    @Test
    public void updateChildren() throws Exception {
        childrenRep.updateChild("name", "surname", 1, 1, 1,1);
        verify(childrenRep, times(1)).updateChild("name", "surname", 1, 1, 1,1);
    }

    @Test
    public void deleteChildren() throws Exception {
        int childId = 1;
        childrenRep.delete(Long.valueOf(childId));
        verify(childrenRep, times(1)).delete(Long.valueOf(childId));
    }

    @Test
    public void updateChildrensFoster() throws Exception {
        int childId = 1;
        int fosterId = 1;
        childrenRep.updateChildrensFoster(childId, fosterId);
        verify(childrenRep, times(1)).updateChildrensFoster(childId, fosterId);
    }

    @Test
    public void updateChildrenByClassId() throws Exception {
        int schoolClassesId = 1;
        int id = 1;
        childrenRep.updateChildrensClasses(schoolClassesId, id);
        verify(childrenRep, times(1)).updateChildrensClasses(schoolClassesId, id);
    }

}