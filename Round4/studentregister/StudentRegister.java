/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

import java.util.*;

public class StudentRegister {
    private HashMap<String, Student> students;
    private HashMap<String, Course> courses;
    private HashMap<String, ArrayList<Attainment>> attainments;

    public StudentRegister() {
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
        this.attainments = new HashMap<>();
    }
    
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students2 = new ArrayList<>();
        for(var entry : students.entrySet()){
            students2.add(entry.getValue());
        }
        
        students2.sort((a,b)-> {
            return a.getName().compareTo(b.getName());
        });
        return students2;
    }

    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses2 = new ArrayList<>();
        for(var entry : courses.entrySet()){
            courses2.add(entry.getValue());
        }
        
        courses2.sort((a,b) -> {
            return a.getName().compareTo(b.getName());
        });
        return courses2;
    }
       
    public void addStudent(Student student){
        students.put(student.getStudentNumber(), student);
    }
    
    public void addCourse(Course course){
        courses.put(course.getCode(), course);
    }
    
    public void addAttainment(Attainment att){
        ArrayList<Attainment> atts = attainments.get(att.getStudentNumber());
        if(atts == null){
            atts = new ArrayList<>();
            atts.add(att);
            attainments.put(att.getStudentNumber(), atts);
        }
        else{
           if(!atts.contains(att)){
               atts.add(att);
           }
        }
    }
    
    public void printStudentAttainments(String studentNumber, String order){
        if(!students.containsKey(studentNumber)){
            
            System.out.println("Unknown student number: " + studentNumber);
        }
        
        else{

            if(order.equals("by name")){
                ArrayList<Attainment> student_atts = new ArrayList<>();
                ArrayList<Course> student_courses = this.getCourses();
                
                for(Course course : student_courses){
                    for(Attainment att : attainments.get(studentNumber)){
                        if(course.getCode().equals(att.getCourseCode())){
                            student_atts.add(att);
                        }
                    }
                }
                
                Student student = students.get(studentNumber); 
                System.out.format("%s (%s):%n", student.getName(), studentNumber);
                for(Attainment att : student_atts){
                    Course course = courses.get(att.getCourseCode());
                    System.out.format("  %s %s: %d%n",
                        course.getCode(), course.getName(), att.getGrade());
                }
            }
            
            else if(order.equals("by code")){
                ArrayList<Attainment> student_atts = attainments.get(studentNumber);
                student_atts.sort((a,b) -> {
                    return a.getCourseCode().compareTo(b.getCourseCode());
                });
                Student student = students.get(studentNumber); 
                System.out.format("%s (%s):%n", student.getName(), studentNumber);
                for(Attainment att : student_atts){
                    Course course = courses.get(att.getCourseCode());
                    System.out.format("  %s %s: %d%n",
                        course.getCode(), course.getName(), att.getGrade());
                }
            }
            
            else{
                ArrayList<Attainment> student_atts = attainments.get(studentNumber);
               Student student = students.get(studentNumber); 
                System.out.format("%s (%s):%n", student.getName(), studentNumber);
                for(Attainment att : student_atts){
                    Course course = courses.get(att.getCourseCode());
                    System.out.format("  %s %s: %d%n",
                        course.getCode(), course.getName(), att.getGrade());
                }
            }

        }
    }
    
    public void printStudentAttainments(String studentNumber){
        if(!attainments.containsKey(studentNumber)){
            System.out.println("Unknown student number: " + studentNumber);
        }
       
        else{
            Student student = students.get(studentNumber); 
            ArrayList<Attainment> student_atts = attainments.get(studentNumber);
            
            System.out.format("%s (%s):%n", student.getName(), studentNumber);
            for(Attainment att : student_atts){
                Course course = courses.get(att.getCourseCode());
                System.out.format("  %s %s: %d%n",
                        course.getCode(), course.getName(), att.getGrade());
            }
            
        }
}
}
