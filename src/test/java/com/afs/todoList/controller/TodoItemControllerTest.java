package com.afs.todoList.controller;

import com.afs.todoList.entity.TodoItem;
import com.afs.todoList.repository.TodoItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(statements = "alter table todo_item alter column id restart with 1")
@SpringBootTest
@AutoConfigureMockMvc
class TodoItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    ObjectMapper jsonMapper;

    @BeforeEach
    void setUp() {
        todoItemRepository.deleteAll();
    }

    @Test
    void should_get_all_todoItem_when_get_given_2_todoItems() throws Exception {
        //given
        TodoItem todoItem1 = new TodoItem("todo1", false);
        TodoItem todoItem2 = new TodoItem("todo2", true);
        todoItemRepository.save(todoItem1);
        todoItemRepository.save(todoItem2);
        //when
        ResultActions resultActions = mockMvc.perform(get("/todos"));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(todoItem1.getId()))
                .andExpect(jsonPath("$[0].text").value(todoItem1.getText()))
                .andExpect(jsonPath("$[0].done").value(todoItem1.getDone()))
                .andExpect(jsonPath("$[1].id").value(todoItem2.getId()))
                .andExpect(jsonPath("$[1].text").value(todoItem2.getText()))
                .andExpect(jsonPath("$[1].done").value(todoItem2.getDone()));
    }

    @Test
    void should_create_todoItem_when_post_given_text() throws Exception {
        //given
        TodoItem todoItem = new TodoItem("todo1", false);
        //when
        ResultActions resultActions = mockMvc.perform(
                post("/todos").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(todoItem))
        );
        //then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value(todoItem.getText()))
                .andExpect(jsonPath("$.done").value(todoItem.getDone()));
    }

    @Test
    void should_update_todoItem_status_when_put_given_change_in_status() throws Exception {
        //given
        TodoItem todoItem = new TodoItem(1, "todo1", false);
        TodoItem updated = todoItemRepository.save(todoItem);
        updated.setDone(true);
        //when
        ResultActions result = mockMvc.perform(
                put(String.format("/todos/%d", updated.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(updated)));
        //then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updated.getId()))
                .andExpect(jsonPath("$.text").value(updated.getText()))
                .andExpect(jsonPath("$.done").value(updated.getDone()));

    }

    @Test
    void should_delete_todoItem_when_delete_given_id() throws Exception {
        //given
        TodoItem todoItem = new TodoItem("todo1", false);
        TodoItem saved = todoItemRepository.save(todoItem);
        //then
        assertEquals(1, todoItemRepository.findAll().size());
        ResultActions result = mockMvc.perform(delete(String.format("/todos/%d", saved.getId())));
        assertEquals(0, todoItemRepository.findAll().size());
        result
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }
}