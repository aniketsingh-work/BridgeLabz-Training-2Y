package com.school.main;

import com.school.data.*;
import com.school.util.*;

public class MainClass {
    public static void main(String[] args) {
        // Create a Student object with marks in 3 subjects
        int[] marks = {85, 92, 78};
        Student student = new Student("Alice Johnson", marks);
        
        // Create an Analyzer object
        Analyzer analyzer = new Analyzer();
        
        // Calculate average
        double average = analyzer.calculateAverage(student);
        
        // Find grade
        String grade = analyzer.findGrade(average);
        
        // Display student details using toString method
        System.out.println(student.toString());
        System.out.println("Average: " + average);
        System.out.println("Grade: " + grade);
    }
}