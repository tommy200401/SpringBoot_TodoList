package com.afs.todoList.service;

import com.afs.todoList.entity.TodoItem;
import com.afs.todoList.repository.TodoItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TodoItemServiceTest {
    @Mock
    TodoItemRepository todoItemRepository;
    @InjectMocks
    TodoItemService todoItemService;

    @Test
    void should_get_all_todoItem_when_get_given_2_todoItems(){
        //given
        List<TodoItem> todoItems = Arrays.asList(new TodoItem("todo1", false)
                , new TodoItem("todo2", true));
        when(todoItemRepository.findAll()).thenReturn(todoItems);
        //when
        List<TodoItem> actualItems = todoItemService.findAll();
        //then
        assertEquals(todoItems, actualItems);
    }

}