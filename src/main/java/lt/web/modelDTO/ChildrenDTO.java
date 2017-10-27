package lt.web.modelDTO;

public class ChildrenDTO {
    private int childId;
    private String name;
    private String surname;
    private FostersDTO foster;
    private SchoolClassesDTO schoolClasse;
    private UsersDTO user;

    public ChildrenDTO() {
    }

    public ChildrenDTO(int childId, String name, String surname, FostersDTO foster) {
        this.childId = childId;
        this.name = name;
        this.surname = surname;
        this.foster = foster;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
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

    public FostersDTO getFoster() {
        return foster;
    }

    public void setFoster(FostersDTO foster) {
        this.foster = foster;
    }

    public SchoolClassesDTO getSchoolClasses() {
        return schoolClasse;
    }

    public void setSchoolClasses(SchoolClassesDTO schoolClasses) {
        this.schoolClasse = schoolClasses;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }
}
