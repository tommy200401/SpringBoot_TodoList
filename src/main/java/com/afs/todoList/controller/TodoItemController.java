package com.afs.todoList.controller;
import com.afs.todoList.mapper.TodoItemMapper;
import com.afs.todoList.dto.TodoItemResponse;
import com.afs.todoList.repository.TodoItemRepository;
import com.afs.todoList.service.TodoItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoItemController {
    private TodoItemRepository todoItemRepository;
    private TodoItemMapper todoItemMapper;

    public TodoItemController(TodoItemRepository todoItemRepository,  TodoItemMapper todoItemMapper) {
        this.todoItemRepository = todoItemRepository;
        this.todoItemMapper = todoItemMapper;
    }

    @GetMapping
    public List<TodoItemResponse> findAllTodoItems () {
        return this.todoItemRepository.findAll().stream().map(item -> todoItemMapper.toResponse(item)).collect(Collectors.toList());
    }

}
