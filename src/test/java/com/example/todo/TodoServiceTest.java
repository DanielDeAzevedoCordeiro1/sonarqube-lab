package com.example.todo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.todo.application.service.TodoService;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.repository.TodoRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TodoServiceTest {

  @Test
  void create_setsTitleAndDefaultDone() {
    TodoRepository repo = Mockito.mock(TodoRepository.class);
    when(repo.save(any(Todo.class))).thenAnswer(inv -> inv.getArgument(0));

    TodoService service = new TodoService(repo);
    Todo created = service.create("Write tests");

    assertThat(created.getTitle()).isEqualTo("Write tests");
    assertThat(created.isDone()).isFalse();
  }

  @Test
  void get_throwsWhenNotFound() {
    TodoRepository repo = Mockito.mock(TodoRepository.class);
    when(repo.findById(99L)).thenReturn(Optional.empty());

    TodoService service = new TodoService(repo);

    assertThatThrownBy(() -> service.get(99L))
        .isInstanceOf(java.util.NoSuchElementException.class);
  }
}
