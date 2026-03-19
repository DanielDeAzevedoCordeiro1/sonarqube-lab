package com.example.todo.application.service;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.repository.TodoRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoService {
  private final TodoRepository repository;
  private static final String NOT_FOUND_MESSAGE = "Todo not found";

  public TodoService(TodoRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public Todo create(String title) {
    return repository.save(new Todo(title));
  }

  @Transactional(readOnly = true)
  public Todo get(Long id) {
    return repository.findById(id).orElseThrow(() -> new NoSuchElementException(NOT_FOUND_MESSAGE));
  }

  @Transactional(readOnly = true)
  public List<Todo> list() {
    return repository.findAll();
  }

  @Transactional
  public Todo update(Long id, String title, boolean done) {
    Todo todo = get(id);
    todo.setTitle(title);
    todo.setDone(done);
    return repository.save(todo);
  }

  @Transactional
  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new NoSuchElementException(NOT_FOUND_MESSAGE);
    }
    repository.deleteById(id);
  }
}
