package com.example.todo.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void shouldCreateTodoCorrectly() {
        Todo todo = new Todo("My Task");
        
        assertNull(todo.getId());
        assertEquals("My Task", todo.getTitle());
        assertFalse(todo.isDone());
    }

    @Test
    void shouldMarkAsDoneCorrectly() {
        Todo todo = new Todo("My Task");
        
        todo.markAsDone();
        
        assertTrue(todo.isDone());
    }

    @Test
    void shouldThrowExceptionWhenMarkingAlreadyDoneTodo() {
        Todo todo = new Todo("My Task");
        todo.markAsDone();
        
        IllegalStateException exception = assertThrows(IllegalStateException.class, todo::markAsDone);
        assertEquals("Tarefa já está concluída.", exception.getMessage());
    }
}