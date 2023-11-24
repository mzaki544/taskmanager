package com.foo.taskmanager.controller;

import com.foo.taskmanager.entity.Task;
import com.foo.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author mahmoud
 */
@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /*
     This endpoint allows users to get all tasks.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    /*
     This endpoint allows users to add tasks.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Task> addTask(@RequestBody Task task){
        return taskService.add(task);
    }

    /*
     This endpoint allows users to update task status.
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Task> updateTaskStatus(@RequestBody Task task, @PathVariable Long id){
        return taskService.updateTaskStatus(task, id);
    }

}
