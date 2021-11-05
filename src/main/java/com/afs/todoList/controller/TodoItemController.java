package com.afs.todoList.controller;
import com.afs.todoList.dto.TodoItemRequest;
import com.afs.todoList.mapper.TodoItemMapper;
import com.afs.todoList.dto.TodoItemResponse;
import com.afs.todoList.service.TodoItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")

public class TodoItemController {
    private final TodoItemService todoItemService;
    private final TodoItemMapper todoItemMapper;

    public TodoItemController(TodoItemService todoItemService,
                              TodoItemMapper todoItemMapper) {
        this.todoItemService = todoItemService;
        this.todoItemMapper = todoItemMapper;
    }

    // Return all todoItem
    @GetMapping
    public List<TodoItemResponse> findAllTodoItems () {
        return this.todoItemService.findAll().stream().map(todoItemMapper::toResponse).collect(Collectors.toList());
    }

    // Post: create item
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoItemResponse createTodoItem (@RequestBody TodoItemRequest todoItemRequest){
        return this.todoItemMapper.toResponse(todoItemService.createTodoItem(todoItemMapper.toEntity(todoItemRequest)));
    }

    // Put: update status
    @PutMapping("/{id}")
    public TodoItemResponse updateTodoItem (@PathVariable ("id") Integer id,
                                    @RequestBody TodoItemRequest updatedTodoItemRequest){
        return todoItemMapper.toResponse(this.todoItemService.updateTodoItem(id,
                todoItemMapper.toEntity(updatedTodoItemRequest)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Integer id) {
        todoItemService.deleteById(id);
    }

}
