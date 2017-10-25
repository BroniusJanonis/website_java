package lt.web.models;

import javax.persistence.*;

@Entity
@Table(name = "webteachers")
public class Teachers {
    private int teacherId;
    private String name;
    private String surname;
    private String phone;
    private String subject;
    private SchoolClasses schoolClasses;
    private Users user;

    public Teachers() {
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

    @Column(name = "SUBJECT", nullable = false, length = 64)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @OneToOne(mappedBy = "teacher")
    public SchoolClasses getSchoolClasses() {
        return schoolClasses;
    }

    public void setSchoolClasses(SchoolClasses schoolClasses) {
        this.schoolClasses = schoolClasses;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
