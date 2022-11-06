package com.example.app_list_of_lessons.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LESSONS")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name="teacher")
    private String teacher;
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "amount_hours")
    private int amount_h;
    @Column(name = "speciality")
    private String speciality;
}
