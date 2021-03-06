package lt.web.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "webschoolclasses")
public class SchoolClasses {
    private int schoolClassesId;
    private String title;
    private List<Children> childrenList;
    private Teachers teacher;

    public SchoolClasses() {
    }

    public SchoolClasses(int schoolClassesId) {
        this.schoolClassesId = schoolClassesId;
    }

    public SchoolClasses(String title) {
        this.title = title;
    }

    public SchoolClasses(int schoolClassesId, String title) {
        this.schoolClassesId = schoolClassesId;
        this.title = title;
    }

    public SchoolClasses(String title, Teachers teacher) {
        this.title = title;
        this.teacher = teacher;
    }

    public SchoolClasses(int schoolClassesId, String title, Teachers teacher) {
        this.schoolClassesId = schoolClassesId;
        this.title = title;
        this.teacher = teacher;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHOOL_CLASSES_ID", unique = true, nullable = false)
    public int getSchoolClassesId() {
        return schoolClassesId;
    }

    public void setSchoolClassesId(int schoolClassesId) {
        this.schoolClassesId = schoolClassesId;
    }


    @Column(name = "TITLE", unique = true, nullable = false, length = 64)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // "schoolClasses" is seteriu turi sutapt
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "schoolClasses")
    public List<Children> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Children> childrenList) {
        this.childrenList = childrenList;
    }

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "TEACHER_ID")
    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }
}
