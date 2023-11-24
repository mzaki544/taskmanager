package com.foo.taskmanager.controller;

import com.foo.taskmanager.entity.Task;
import com.foo.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = TaskController.class)
@Import(TaskService.class)
public class TaskControllerTest {

    @MockBean
    TaskService service;

    @Autowired
    private WebTestClient webClient;

    @Test
    public void addTaskTest(){
        Mono<Task> taskMono = Mono.just(new Task(1L, "task1", false));
        Mockito.when(service.add(new Task(1L, "task1", false))).thenReturn(taskMono);
        webClient.post()
                .uri("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .body(taskMono, Task.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void getAllTasksTest(){
        Flux<Task> tasks = Flux.just(new Task(1L, "task1", false),
                new Task(2L, "task2", false),
                new Task(3L, "task3", false));

        Mockito.when(service.getAllTasks()).thenReturn(tasks);

        Flux<Task> result = webClient.get().uri("/tasks")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Task.class)
                .getResponseBody();
        StepVerifier.create(result).expectSubscription()
                .expectNext(new Task(1L, "task1", false))
                .expectNext(new Task(2L, "task2", false))
                .expectNext(new Task(3L, "task3", false))
                .verifyComplete();
    }

    @Test
    public void updateTaskStatusTest(){
        Mono<Task> taskMono = Mono.just(new Task(1L, "task1", false));
        Mockito.when(service.updateTaskStatus(new Task(1L, "task1", false), 1L)).thenReturn(taskMono);
        webClient.put()
                .uri("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(taskMono, Task.class)
                .exchange()
                .expectStatus().isOk();
    }
}
