package org.example.springpractice.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.todo.dto.TodoRequest;
import org.example.springpractice.todo.dto.TodoResponse;
import org.example.springpractice.todo.entity.Todo;
import org.example.springpractice.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoResponse save(TodoRequest todoRequest) {
        Todo todo = new Todo(todoRequest.getContent());
        Todo savedTodo = todoRepository.save(todo);

        return new TodoResponse(
                savedTodo.getId(),
                savedTodo.getContent(),
                savedTodo.getCreatedAt(),
                savedTodo.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<TodoResponse> findTodos() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoResponse> dtos = new ArrayList<>();

        for (Todo todo : todos) {
            TodoResponse todoResponse = new TodoResponse(
                    todo.getId(),
                    todo.getContent(),
                    todo.getCreatedAt(),
                    todo.getModifiedAt()
            );
            dtos.add(todoResponse);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public TodoResponse findTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 Todo가 없습니다.")
        );
        return new TodoResponse(
                todo.getId(),
                todo.getContent(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    @Transactional
    public TodoResponse updateTodoContent(Long id, TodoRequest todoRequest) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 Todo가 없습니다.")
        );
        todo.updateTodoContent(todoRequest.getContent());
        return new TodoResponse(
                todo.getId(),
                todo.getContent(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    @Transactional
    public void deleteTodo(Long todoId) {
        boolean b = todoRepository.existsById(todoId);
        if (!b) {
            throw new IllegalArgumentException("해당하는 Todo가 없습니다.");
        }
        todoRepository.deleteById(todoId);
    }
}
