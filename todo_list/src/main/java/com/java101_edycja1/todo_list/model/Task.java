package com.java101_edycja1.todo_list.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="todo_tasks")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dueDate;
    private Boolean isComplete;
    // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Task> subtasks = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("Task{id=%d, description='%s', dueDate='%s', isComplete='%s'}", id, description, dueDate, isComplete);
    }

    public Task(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isComplete = false;
    }
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.dueDate = null;
    }

    public Task() {
        this.title = null;
        this.description = null;
        this.dueDate = null;
        this.isComplete = false;
    }

    /*
    public void addSubtask(Task subtask) {
        this.subtasks.add(subtask);
    }
     */
}