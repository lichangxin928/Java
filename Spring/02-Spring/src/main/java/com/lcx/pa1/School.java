package com.lcx.pa1;

public class School {
    Student student;

    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "School{" +
                "student=" + student +
                '}';
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
