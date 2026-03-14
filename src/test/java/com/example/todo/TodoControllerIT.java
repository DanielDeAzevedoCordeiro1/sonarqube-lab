package com.example.todo;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.todo.application.api.TodoController.CreateTodoRequest;
import com.example.todo.application.api.TodoController.UpdateTodoRequest;
import com.example.todo.domain.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TodoControllerIT {

  @Autowired
  private TestRestTemplate rest;

  @Test
  void fullCrudFlow() {
    ResponseEntity<Todo> created = rest.postForEntity("/todos", new CreateTodoRequest("Comprar cafe"), Todo.class);
    assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    Long id = created.getBody().getId();

    ResponseEntity<Todo> fetched = rest.getForEntity("/todos/" + id, Todo.class);
    assertThat(fetched.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(fetched.getBody().getTitle()).isEqualTo("Comprar cafe");

    rest.put("/todos/" + id, new UpdateTodoRequest("Comprar cafe e leite", true));

    ResponseEntity<Todo> updated = rest.getForEntity("/todos/" + id, Todo.class);
    assertThat(updated.getBody().getTitle()).isEqualTo("Comprar cafe e leite");
    assertThat(updated.getBody().isDone()).isTrue();

    rest.delete("/todos/" + id);
    ResponseEntity<String> afterDelete = rest.getForEntity("/todos/" + id, String.class);
    assertThat(afterDelete.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }
}
