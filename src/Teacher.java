public class Teacher extends Person {
    private String field;
    private String faculty;

    /*Constructor*/
    public Teacher(){
        super();
        this.field = null;
        this.faculty = null;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getField(){
        return field;
    }

    public void setFaculty(String faculty){
        this.faculty = faculty;
    }

    public String getFaculty(){
        return faculty;
    }
}
