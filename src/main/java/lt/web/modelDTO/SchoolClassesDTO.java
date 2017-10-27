package lt.web.modelDTO;

import java.util.List;

public class SchoolClassesDTO {
    private int schoolClassesId;
    private String subject;
    private String title;
    private List<ChildrenDTO> childrenList;
    private TeachersDTO teacher;

    public SchoolClassesDTO() {
    }

    public SchoolClassesDTO(int schoolClassesId, String subject, String title, List<ChildrenDTO> childrenList) {
        this.schoolClassesId = schoolClassesId;
        this.subject = subject;
        this.title = title;
        this.childrenList = childrenList;
    }

    public int getSchoolClassesId() {
        return schoolClassesId;
    }

    public void setSchoolClassesId(int schoolClassesId) {
        this.schoolClassesId = schoolClassesId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ChildrenDTO> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<ChildrenDTO> childrenList) {
        this.childrenList = childrenList;
    }

    public TeachersDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeachersDTO teacher) {
        this.teacher = teacher;
    }
}
