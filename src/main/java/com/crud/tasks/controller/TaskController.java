package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.task.facade.TaskFacade;
import com.crud.tasks.view.TaskView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TaskController {

    private final TaskFacade taskFacade;

    @JsonView(TaskView.Internal.class)
    @RequestMapping(method = RequestMethod.GET, value = "tasks")
    public List<TaskDto> getTasks(){
        return taskFacade.fetchAllTasks();
    }
    @RequestMapping(method = RequestMethod.GET, value = "tasks/{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return taskFacade.fetchTasks(taskId);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "tasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        return taskFacade.updateTask(taskDto);
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = "tasks", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto){
        taskFacade.saveTask(taskDto);
    }
    @DeleteMapping(value = "tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        taskFacade.deleteTask(taskId);
    }
}