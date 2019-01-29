package com.javarush.task.task29.task2909.human;

public class UniversityPerson extends Human {
    private University university;

    public UniversityPerson(String name, int age) {
        super(name, age);
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public University getUniversity() {
        return university;
    }

    public String getName() {
        return this.name;
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
}
