package com.example.app_list_of_lessons.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;

import javax.persistence.*;
import java.time.LocalDateTime;


@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_actions")
public class Actions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_actions")
    private LocalDateTime date_actions;
    @Column(name = "description")
    private String description;
    public Actions(LocalDateTime dateTime, String description){
        this.date_actions=dateTime;
        this.description = description;
    }
}
