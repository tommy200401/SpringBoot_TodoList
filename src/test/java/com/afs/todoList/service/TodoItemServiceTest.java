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
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TodoItemServiceTest {
    @Mock
    TodoItemRepository todoItemRepository;
    @InjectMocks
    TodoItemService todoItemService;

    @Test
    void should_get_all_todoItem_when_get_given_2_todoItems(){
        //given
        List<TodoItem> todoItems = Arrays.asList(
                new TodoItem("todo1", false),
                new TodoItem("todo2", true));
        when(todoItemRepository.findAll()).thenReturn(todoItems);
        //when
        List<TodoItem> actualItems = todoItemService.findAll();
        //then
        assertEquals(todoItems, actualItems);
    }

    @Test
    void should_create_todoItem_when_post_given_text() {
        //given
        TodoItem todoItem = new TodoItem("todo1", false);
        when(todoItemRepository.save(todoItem)).thenReturn(todoItem);
        //when
        TodoItem actual =
                todoItemService.createTodoItem(todoItem);
        //then
        assertEquals(todoItem.getText(), actual.getText());
        assertEquals(todoItem.getDone(), actual.getDone());
    }

    @Test
    void should_update_todoItem_status_when_put_given_change_in_status() {
        //given
        TodoItem todoItem = new TodoItem("todo1", false);
        todoItemRepository.save(todoItem);
        TodoItem todoItemUpdated = new TodoItem("todo1", true);
        when(todoItemRepository.save(todoItemUpdated)).thenReturn(todoItemUpdated);
        //when
        TodoItem actual = todoItemRepository.save(todoItemUpdated);
        //then
        assertEquals(todoItemUpdated, actual);
    }

    @Test
    void should_delete_todoItem_when_delete_given_id() {
        //given
        willDoNothing().given(todoItemRepository).deleteById(anyInt());
        todoItemRepository.deleteById(1);
        //then
        verify(todoItemRepository).deleteById(anyInt());
        verifyNoMoreInteractions(todoItemRepository);
    }

}