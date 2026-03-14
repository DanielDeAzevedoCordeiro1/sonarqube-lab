package com.example.todo.infra.persistence;

import com.example.todo.domain.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<Todo, Long> {
}
