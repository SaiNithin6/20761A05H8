package com.example.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Task")
public class Task extends PanacheEntity {
    
    @Column(nullable = false)
    public String title;
    
    @Column(length = 1000)
    public String description;
    
    @Column(nullable = false)
    public boolean completed = false;
    
    @Column(nullable = false)
    public LocalDateTime createdAt;
    
    @Column(nullable = false)
    public LocalDateTime updatedAt;
    
    // Default constructor
    public Task() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Constructor with parameters
    public Task(String title, String description) {
        this();
        this.title = title;
        this.description = description;
    }
    
    // Constructor with all parameters
    public Task(String title, String description, boolean completed) {
        this(title, description);
        this.completed = completed;
    }
    
    // Update timestamp when task is modified
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }
    
    // Custom finder methods using Panache
    public static Task findByTitle(String title) {
        return find("title", title).firstResult();
    }
    
    public static long countCompleted() {
        return count("completed", true);
    }
    
    public static long countPending() {
        return count("completed", false);
    }
}