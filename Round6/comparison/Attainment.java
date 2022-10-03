
import java.util.Comparator;


public class Attainment implements Comparable<Attainment>{
    private final String courseCode;
    private final String studentNumber;
    private final int grade;
    public static final Comparator<Attainment> CODE_STUDENT_CMP = Comparator.comparing(Attainment::getCourseCode).thenComparing(Attainment::getStudentNumber);
    public static final Comparator<Attainment> CODE_GRADE_CMP = Comparator.comparing(Attainment::getCourseCode).thenComparing(Attainment::getGrade);

    public Attainment(String courseCode, String studentNumber, int grade) {
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public int getGrade() {
        return grade;
    }
    
    @Override
    public String toString(){
        return String.format("%s %s %d%n", courseCode, studentNumber, grade);
    }
    
    @Override
    public int compareTo(Attainment other){
        int cmp = studentNumber.compareTo(other.studentNumber);
        if(cmp == 0){
            cmp = courseCode.compareTo(other.courseCode);
        }
        return cmp;
    }  
    
}
   
