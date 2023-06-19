package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

//Database (MySQL)
//Static List of todos => Database (H2, MySQL)
//JPA
// Bean -> Database Table

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private int id;
    private String username;

    /*
    * We are able to fo the validation on Size because we added spring-boot-starter-validation in the pom.xml file.
    * */
    @Size(min = 10, message = "Enter atleast 10 characters")
    private String description;
    private LocalDate date;
    private boolean done;

    public Todo(){}
    public Todo(int id, String username, String description, LocalDate date, boolean done) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.date = date;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", done=" + done +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
