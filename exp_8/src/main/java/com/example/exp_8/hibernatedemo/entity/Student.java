package com.example.exp_8.hibernatedemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "STUDENT")
public class Student {
    
    @Id
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "AGE")
    private Integer age;
    
    // Default constructor required by Hibernate
    public Student() {
    }

    // Constructor with all fields
    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}