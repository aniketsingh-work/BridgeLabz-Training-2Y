package com.school.data;

public class Student {
    private String name;
    private int[] marks;
    
    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }
    
    public String getName() {
        return name;
    }
    
    public int[] getMarks() {
        return marks;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setMarks(int[] marks) {
        this.marks = marks;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student Name: ").append(name).append("\n");
        sb.append("Marks: ");
        for (int i = 0; i < marks.length; i++) {
            sb.append(marks[i]);
            if (i < marks.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}