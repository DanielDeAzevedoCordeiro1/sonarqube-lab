package com.example.todo.infra.persistence;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.repository.TodoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepositoryAdapter implements TodoRepository {
  private final TodoJpaRepository jpaRepository;

  public TodoRepositoryAdapter(TodoJpaRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public Todo save(Todo todo) {
    return jpaRepository.save(todo);
  }

  @Override
  public Optional<Todo> findById(Long id) {
    return jpaRepository.findById(id);
  }

  @Override
  public List<Todo> findAll() {
    return jpaRepository.findAll();
  }

  @Override
  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }

  @Override
  public boolean existsById(Long id) {
    return jpaRepository.existsById(id);
  }
}
