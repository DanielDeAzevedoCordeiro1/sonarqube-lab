package com.example.todo.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "todos")
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String title;

  private boolean done;

  protected Todo() { }

  public Todo(String title) {
    this.title = title;
    this.done = false;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public boolean isDone() {
    return done;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDone(boolean done) {
    this.done = done;
    System.out.println("IsDone: " + this.done);
  }

  public void markAsDone() {
    if (this.done) {
      throw new IllegalStateException("Tarefa já está concluída.");
    }
    this.done = true;
  }
}
