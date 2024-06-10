package com.Redcorpmicroservice.task.demo.service;

import com.Redcorpmicroservice.task.demo.model.Task;

import java.util.List;

public interface TaskService {

    public abstract Task createTask(Task task);
    public abstract Task getTaskById(Long task_id);
    public abstract Task updateTask(Task task);
    public abstract void deleteTask(Long task_id);
    public abstract List<Task> getAllTasks();

    List<Task> findByProjectId(Long projectId);
    List<Task> findByEmployeeId(Long employeeId);
}
