package com.Redcorpmicroservice.task.demo.service.impl;

import com.Redcorpmicroservice.task.demo.exception.ValidationException;
import com.Redcorpmicroservice.task.demo.model.Task;
import com.Redcorpmicroservice.task.demo.repository.TaskRepository;
import com.Redcorpmicroservice.task.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        validateTask(task);
        return  taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long task_id) {
        if(taskRepository.existsById(task_id)) {
            return taskRepository.findById(task_id).orElse(null);
        }
        else
            throw new ValidationException("No existe la tarea");
    }

    @Override
    public Task updateTask(Task task) {
        if(taskRepository.existsById(task.getId())) {
            validateTask(task);
            return taskRepository.save(task);
        }
        else
            throw new ValidationException("No existe la tarea");
    }

    @Override
    public void deleteTask(Long task_id) {
        taskRepository.deleteById(task_id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findByProjectId(Long projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }

    @Override
    public List<Task> findByEmployeeId(Long employeeId) {
        return taskRepository.findAllByEmployeeId(employeeId);
    }

    private void validateTask(Task task){

        List<String> validStates = Arrays.asList("In progress", "Completed", "To Do","In revision");

        if(task.getTaskName() == null || task.getTaskName().isEmpty()){
            throw new ValidationException("El nombre de la tarea debe ser obligatorio");
        }
        if(task.getTaskName().length() > 50 && task.getTaskName().length()<3) {
            throw new ValidationException("El nombre de la tarea es muy corto o muy largo");
        }
        if(task.getTaskDescription() == null || task.getTaskDescription().isEmpty()){
            throw new ValidationException("La descripción de la tarea debe ser obligatoria");
        }
        if(task.getTaskDescription().length() > 100){
            throw  new ValidationException("La descripción de la tarea es demasiado largo");
        }
        if(task.getTaskInitialDate() == null){
            throw new ValidationException("La fecha de inicio es obligatoria");
        }
        if(task.getTaskFinalDate() == null){
            throw new ValidationException("La fecha de fin es obligatoria");
        }
        if(task.getTaskState() == null || task.getTaskState().isEmpty()) {
            throw new ValidationException("El estado de la tarea  es obligatorio");
        }
        if(!validStates.contains(task.getTaskState())){
            throw new ValidationException("El estado de la tarea  no es válido");
        }
        if(task.getProjectId() == null || task.getProjectId().describeConstable().isEmpty()) {
            throw new ValidationException("El projecto asignado tiene que ser válido");
        }
        if(task.getEmployeeId() == null || task.getEmployeeId().describeConstable().isEmpty()) {
            throw new ValidationException("El empleado asignado tiene que ser válido");
        }
    }
}
