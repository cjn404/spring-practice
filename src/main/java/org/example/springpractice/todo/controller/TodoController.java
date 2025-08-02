package org.example.springpractice.todo.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.todo.dto.TodoRequest;
import org.example.springpractice.todo.dto.TodoResponse;
import org.example.springpractice.todo.repository.TodoRepository;
import org.example.springpractice.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoResponse> createTodo(
            @RequestBody TodoRequest request
    ) {
        return ResponseEntity.ok(todoService.save(request));
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponse> getTodo(
            @PathVariable Long todoId
    ) {
        return ResponseEntity.ok(todoService.findTodo(todoId));
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponse> updateTodoContent(
            @PathVariable Long todoId,
            @RequestBody TodoRequest request
    ) {
        return ResponseEntity.ok(todoService.updateTodoContent(todoId, request));
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long todoId
    ) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok().build();
    }
}
