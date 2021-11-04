package com.afs.todoList.service;

import com.afs.todoList.entity.TodoItem;
import com.afs.todoList.repository.TodoItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemService {
    private final TodoItemRepository todoItemRepository;

    public TodoItemService(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    public List<TodoItem> findAll(){
        return this.todoItemRepository.findAll();}


}
