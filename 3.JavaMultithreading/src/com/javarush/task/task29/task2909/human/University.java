package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class University {
    private String name;
    private int age;
    private List<Student> students;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
        students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student :
                students) {
            if (student.getAverageGrade() == averageGrade) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        double max= 0.0;
        Student stud = null;
        for (Student student :
                students) {
            if (student.getAverageGrade() > max) {
                stud = student;
                max = student.getAverageGrade();
            }
        }
        return stud;
    }

    public Student getStudentWithMinAverageGrade() {
        double min = students.get(0).getAverageGrade();
        Student stud = null;
        for (Student student :
                students) {
            if (student.getAverageGrade() < min) {
                stud = student;
                min = student.getAverageGrade();
            }
        }
        return stud;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}