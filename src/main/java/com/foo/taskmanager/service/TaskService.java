package com.foo.taskmanager.service;

import com.foo.taskmanager.entity.Task;
import com.foo.taskmanager.repository.TaskRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Mono<Task> add(Task task) {
        return taskRepository.save(task);
    }
    
    public Mono<Task> updateTaskStatus(Task task, Long id){
        return taskRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(savedTask -> {
                    if (savedTask.isPresent()) {
                       task.setStatus(!task.isStatus());
                        return taskRepository.save(task);
                    }
                    return Mono.empty();
                });
    }

    public Flux<Task> getAllTasks() {
        return taskRepository.findAll();
    }

}
