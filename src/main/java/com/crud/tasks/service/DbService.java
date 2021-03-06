package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    private final TaskRepository repository;

    @Autowired
    public DbService(TaskRepository repository) {
        this.repository = repository;
    }


    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Optional<Task> getTaskById(final Long taskId){
        return repository.findById(taskId);
    }

    public void removeTask(final Long taskId){
        repository.deleteById(taskId);
    }
    public Task saveTask(final Task task){
        return repository.save(task);
    }
}