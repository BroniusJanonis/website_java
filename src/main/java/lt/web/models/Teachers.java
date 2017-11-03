package lt.web.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "webteachers")
public class Teachers {
    private int teacherId;
    private String name;
    private String surname;
    private String phone;
    private List<Subjects> subject;
    private SchoolClasses schoolClasses;
    private Users user;

    public Teachers() {
    }

    public Teachers(int teacherId) {
        this.teacherId = teacherId;
    }

    public Teachers(String name, String surname, String phone, Users user) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.user = user;
    }

    public Teachers(String name, String surname, String phone, List<Subjects> subject, Users user) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.subject = subject;
        this.user = user;
    }

    public Teachers(int teacherId, String name, String surname, String phone, List<Subjects> subject) {
        this.teacherId = teacherId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.subject = subject;
    }

    public Teachers(String name, String surname, String phone, List<Subjects> subject, SchoolClasses schoolClasses, Users user) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.subject = subject;
        this.schoolClasses = schoolClasses;
        this.user = user;
    }

    public Teachers(int teacherId, String name, String surname, String phone, List<Subjects> subject, Users user) {
        this.teacherId = teacherId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.subject = subject;
        this.user = user;
    }

    public Teachers(int teacherId, String name, String surname, String phone, List<Subjects> subject, SchoolClasses schoolClasses, Users user) {
        this.teacherId = teacherId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.subject = subject;
        this.schoolClasses = schoolClasses;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEACHER_ID", unique = true, nullable = false)
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Column(name = "NAME", nullable = false, length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SURNAME", nullable = false, length = 64)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "PHONE", nullable = false, length = 64)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "teacher")
    public List<Subjects> getSubject() {
        return subject;
    }

    public void setSubject(List<Subjects> subject) {
        this.subject = subject;
    }

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "teacher")
    public SchoolClasses getSchoolClasses() {
        return schoolClasses;
    }

    public void setSchoolClasses(SchoolClasses schoolClasses) {
        this.schoolClasses = schoolClasses;
    }

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "USER_ID")
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
