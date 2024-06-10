package com.Redcorpmicroservice.task.demo.controller;

import com.Redcorpmicroservice.task.demo.model.Task;
import com.Redcorpmicroservice.task.demo.model.dto.TaskRequest;
import com.Redcorpmicroservice.task.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redcorp/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody TaskRequest taskRequest) {
        Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskDescription(taskRequest.getTaskDescription());

        taskService.createTask(task);
    }

    @GetMapping
    public ResponseEntity<?> findAllTask(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> findById(@PathVariable Long taskId)
    {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable(name = "taskId") Long taskId,@RequestBody TaskRequest taskRequest)
    {
        Task task = taskService.getTaskById(taskId);

        if(task == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        task.setTaskName(taskRequest.getTaskName());
        task.setTaskDescription(taskRequest.getTaskDescription());
        task.setTaskInitialDate(taskRequest.getTaskInitialDate());
        task.setTaskFinalDate(taskRequest.getTaskFinalDate());
        task.setTaskState(taskRequest.getTaskState());
        task.setEmployeeId(taskRequest.getEmployeeId());
        task.setProjectId(taskRequest.getProjectId());

        return new ResponseEntity<Task>(taskService.updateTask(task),HttpStatus.OK);
    }

    @GetMapping("/search-by-employee/{employeeId}")
    public ResponseEntity<?> findByEmployeeId(@PathVariable Long employeeId){
        return ResponseEntity.ok(taskService.findByEmployeeId(employeeId));
    }


    @GetMapping("/search-by-project/{projectId}")
    public ResponseEntity<?> findByProjectId(@PathVariable Long projectId){
        return  ResponseEntity.ok(taskService.findByProjectId(projectId));
    }
}
