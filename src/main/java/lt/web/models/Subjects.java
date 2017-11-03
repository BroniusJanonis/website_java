package lt.web.models;

import javax.persistence.*;

@Entity
@Table(name = "websubjects")
public class Subjects {
    int subjectId;
    String subjectName;
    Teachers teacher;

    public Subjects() {
    }

    public Subjects(String subjectName) {
        this.subjectName = subjectName;
    }

    public Subjects(int subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public Subjects(String subjectName, Teachers teacher) {
        this.subjectName = subjectName;
        this.teacher = teacher;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBJECT_ID", unique = true, nullable = false)
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

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEACHER_ID")
    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }
}
