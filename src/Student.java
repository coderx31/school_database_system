public class Student extends Person {
    private String year;
    private String course;


    /*Constructor*/
    public Student(){
        super();
        this.year = null;
        this.course = null;
    }

    public void setYear(String year){
        this.year = year;
    }

    public String getYear(){
        return year;
    }

    public void setCourse(String course){
        this.course = course;
    }

    public String getCourse(){
        return course;
    }
}
