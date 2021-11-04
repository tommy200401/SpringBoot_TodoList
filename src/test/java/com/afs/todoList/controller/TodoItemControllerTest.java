package com.afs.todoList.controller;

import com.afs.todoList.entity.TodoItem;
import com.afs.todoList.repository.TodoItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodoItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    ObjectMapper jsonMapper;

//    @BeforeEach
//    void setUp(){
//        TodoItemRepository.deleteAll();
//    }

    @Test
    void should_get_all_todoItem_when_get_given_2_todoItems() throws Exception{
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
                .andExpect(jsonPath("$[0].text").value("todo1"))
                .andExpect(jsonPath("$[0].done").value(false))
                .andExpect(jsonPath("$[1].text").value("todo2"))
                .andExpect(jsonPath("$[1].done").value(true));
    }


}