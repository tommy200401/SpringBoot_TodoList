package com.afs.todoList.service;

import com.afs.todoList.entity.TodoItem;
import com.afs.todoList.exception.TodoItemNotFoundException;
import com.afs.todoList.repository.TodoItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemService {
    private final TodoItemRepository todoItemRepository;

    public TodoItemService(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    public List<TodoItem> findAll() {
        return this.todoItemRepository.findAll();
    }

    public TodoItem createTodoItem(TodoItem todoItem) {
        return this.todoItemRepository.save(todoItem);
    }

    public TodoItem updateTodoItem(Integer id, TodoItem updatedTodoItem) {
        TodoItem originTodoItem =
                todoItemRepository.findById(id).orElseThrow(TodoItemNotFoundException::new);
        if (updatedTodoItem.getText() != null) {
            originTodoItem.setText(updatedTodoItem.getText());
        }
        if (updatedTodoItem.getDone() != null) {
            originTodoItem.setDone(updatedTodoItem.getDone());
        }

        return this.todoItemRepository.save(originTodoItem);
    }

    public void deleteById(Integer id) {
        todoItemRepository.deleteById(id);
    }
}
