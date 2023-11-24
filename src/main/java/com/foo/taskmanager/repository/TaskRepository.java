package com.foo.taskmanager.repository;

import com.foo.taskmanager.entity.Task;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mahmoud
 */
@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task,Long>{
    
}
