package com.school.util;

import com.school.data.Student;

public class Analyzer {
    public double calculateAverage(Student s) {
        int[] marks = s.getMarks();
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return (double) sum / marks.length;
    }
    
    public String findGrade(double average) {
        if (average >= 90) {
            return "A+";
        } else if (average >= 80) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 60) {
            return "C";
        } else if (average >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}