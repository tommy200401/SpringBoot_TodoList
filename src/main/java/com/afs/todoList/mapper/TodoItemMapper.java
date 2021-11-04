package com.afs.todoList.mapper;

import com.afs.todoList.dto.TodoItemResponse;
import com.afs.todoList.entity.TodoItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoItemMapper {
    public TodoItemResponse toResponse(TodoItem todoItem) {
        TodoItemResponse todoItemResponse = new TodoItemResponse();
        BeanUtils.copyProperties(todoItem, todoItemResponse);
        return todoItemResponse;
    }
}
