package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.task.facade.TaskFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskFacade taskFacade;
    @Test
    public void shouldFetchAllTasks() throws Exception{
        //Given
        List<TaskDto> taskList = new ArrayList<>();
        taskList.add(new TaskDto(1L,"Test Title", "Task test"));
        taskList.add(new TaskDto(2L,"Test Title", "Task test"));
        when(taskFacade.fetchAllTasks()).thenReturn(taskList);
        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)));
    }
    @Test
    public void shouldFetchTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L, "TaskDto", "TaskDto");
        when(taskFacade.fetchTasks(1L)).thenReturn(taskDto);
        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("TaskDto")))
                .andExpect(jsonPath("$.content", is("TaskDto")));
    }

    @Test
    public void shouldUpdateTask() throws Exception{
        //Given
        TaskDto mainTaskDto = new TaskDto(1L, "TaskDto", "TaskDto");
        TaskDto mainTaskDto1 = new TaskDto(1L, "TaskDto", "TaskDto2");
        when(taskFacade.updateTask(mainTaskDto)).thenReturn(mainTaskDto1);
        //When & Then
        assertEquals("TaskDto", mainTaskDto.getContent());
        mockMvc.perform(put("/v1/tasks").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).content(asJsonString(mainTaskDto)))
                .andExpect(status().is(200))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldCreateTask() throws Exception{
        //Given
        TaskDto mainTaskDto = new TaskDto(1L, "TaskDto", "TaskDto");
        //When & Then
        mockMvc.perform(post("/v1/tasks").contentType(MediaType.APPLICATION_JSON).content(asJsonString(mainTaskDto)))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldDeleteTask() throws Exception{
        //Given
        TaskDto mainTaskDto = new TaskDto(1L, "TaskDto", "TaskDto");
        String url = "/v1/tasks/"+mainTaskDto.getId();
        //When & Then
        mockMvc.perform(delete(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}