package com.crud.tasks.task.facade;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class TaskFacade {
    private final DbService service;
    private final TaskMapper taskMapper;

    public TaskFacade(DbService service, TaskMapper taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }

    public List<TaskDto> fetchAllTasks(){
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    public TaskDto fetchTasks(Long id) throws TaskNotFoundException {
        Task tasks = service.getTaskById(id).orElseThrow(TaskNotFoundException::new);
        return taskMapper.mapToTaskDto(tasks);
    }

    public TaskDto updateTask(TaskDto taskDto){
        Task updatedTask = service.saveTask(taskMapper.mapToTask(taskDto));
        return taskMapper.mapToTaskDto(updatedTask);
    }

    public void saveTask(TaskDto taskDto){
        service.saveTask(taskMapper.mapToTask(taskDto));
    }

    public void deleteTask(Long taskId){
        service.removeTask(taskId);
    }

}
