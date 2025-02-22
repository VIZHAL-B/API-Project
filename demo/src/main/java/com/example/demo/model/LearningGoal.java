package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class LearningGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goalName;
    private LocalDate targetCompletionDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public LearningGoal() {
    }

    public LearningGoal(String goalName, LocalDate targetCompletionDate, User user) {
        this.goalName = goalName;
        this.targetCompletionDate = targetCompletionDate;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public LocalDate getTargetCompletionDate() {
        return targetCompletionDate;
    }

    public void setTargetCompletionDate(LocalDate targetCompletionDate) {
        this.targetCompletionDate = targetCompletionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
