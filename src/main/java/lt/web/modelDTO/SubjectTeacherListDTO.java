package lt.web.modelDTO;

import lt.web.models.Teachers;

import java.util.List;

public class SubjectTeacherListDTO {
    int subjectId;
    String subjectName;
    List<Teachers> teachersList;

    public SubjectTeacherListDTO() {
    }

    public SubjectTeacherListDTO(int subjectId, String subjectName, List<Teachers> teachersList) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.teachersList = teachersList;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<Teachers> getTeachersList() {
        return teachersList;
    }

    public void setTeachersList(List<Teachers> teachersList) {
        this.teachersList = teachersList;
    }
}
