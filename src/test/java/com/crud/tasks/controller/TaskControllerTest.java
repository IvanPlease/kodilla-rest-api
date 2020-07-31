package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private DbService dbService;
    @Test
    public void shouldFetchAllTasks() throws Exception{
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L,"Test Title", "Task test"));
        taskList.add(new Task(2L,"Test Title", "Task test"));
        when(dbService.getAllTasks()).thenReturn(taskList);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)));
    }
    @Test
    public void shouldFetchTask() throws Exception{
        //Given
        //When & Then
    }
    @Test
    public void shouldUpdateTask() throws Exception{
        //Given
        //When & Then
    }
    @Test
    public void shouldCreateTask() throws Exception{
        //Given
        //When & Then
    }
    @Test
    public void shouldDeleteTask() throws Exception{
        //Given
        //When & Then
    }
}