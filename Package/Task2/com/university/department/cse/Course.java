package com.university.department.cse;

public class Course {
    private String courseName;
    private String courseCode;
    private int credits;
    
    public Course(String courseName, String courseCode, int credits) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
    }
    
    public void printCourseDetails() {
        System.out.println("Course Name: " + courseName);
        System.out.println("Course Code: " + courseCode);
        System.out.println("Credits: " + credits);
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public int getCredits() {
        return credits;
    }
}