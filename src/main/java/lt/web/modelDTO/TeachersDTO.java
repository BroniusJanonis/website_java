package lt.web.modelDTO;

import java.util.List;

public class TeachersDTO {
    private int teacherId;
    private String name;
    private String surname;
    private String phone;
    private List<SubjectsDTO> subject;
    private SchoolClassesDTO schoolClasses;
    private UsersDTO user;

    public TeachersDTO() {
    }

    public TeachersDTO(int teacherId, String name, String surname, String phone, List<SubjectsDTO> subject, SchoolClassesDTO schoolClasses, UsersDTO user) {
        this.teacherId = teacherId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.subject = subject;
        this.schoolClasses = schoolClasses;
        this.user = user;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<SubjectsDTO> getSubject() {
        return subject;
    }

    public void setSubject(List<SubjectsDTO> subject) {
        this.subject = subject;
    }

    public SchoolClassesDTO getSchoolClasses() {
        return schoolClasses;
    }

    public void setSchoolClasses(SchoolClassesDTO schoolClasses) {
        this.schoolClasses = schoolClasses;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }
}
