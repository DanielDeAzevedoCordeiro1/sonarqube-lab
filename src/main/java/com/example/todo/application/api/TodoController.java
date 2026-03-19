package com.example.todo.application.api;

import com.example.todo.application.service.TodoService;
import com.example.todo.domain.model.Todo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {
  
  private final TodoService service;

  public TodoController(TodoService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Todo> create(@Valid @RequestBody CreateTodoRequest request) {
    Todo created = service.create(request.title());
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @GetMapping("/{id}")
  public Todo get(@PathVariable Long id) {
    return service.get(id);
  }

  @GetMapping
  public List<Todo> list() {
    return service.list();
  }

  @PutMapping("/{id}")
  public Todo update(@PathVariable Long id, @Valid @RequestBody UpdateTodoRequest request) {
    return service.update(id, request.title(), request.done());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler
  public ResponseEntity<Map<String, String>> handleNotFound(java.util.NoSuchElementException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
    return ResponseEntity.badRequest().body(Map.of("error", "Validation failed"));
  }

  public record CreateTodoRequest(@NotBlank String title) {
  }

  public record UpdateTodoRequest(@NotBlank String title, boolean done) {
  }
}
