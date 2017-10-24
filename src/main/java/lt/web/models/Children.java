package lt.web.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "webchildren")
public class Children {
    private int childId;
    private String name;
    private String surname;
    private Fosters foster;
    private SchoolClasses schoolClasse;
    private Users user;

    public Children() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHILD_ID", unique = true, nullable = false)
    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOSTER_ID")
    public Fosters getFoster() {
        return foster;
    }

    public void setFoster(Fosters foster) {
        this.foster = foster;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_CLASSES_ID")
    public SchoolClasses getSchoolClasses() {
        return schoolClasse;
    }

    public void setSchoolClasses(SchoolClasses schoolClasses) {
        this.schoolClasse = schoolClasses;
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
