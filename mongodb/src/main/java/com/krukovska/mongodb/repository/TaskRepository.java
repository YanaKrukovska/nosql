package com.krukovska.mongodb.repository;

import com.krukovska.mongodb.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    Task findTaskById(String id);

    List<Task> findAllByDeadlineBefore(LocalDateTime dateTime);

    @Query("{category:'?0'}")
    List<Task> findAllCategoryTasks(String category);

    List<Task> findAllByDescriptionContains(String word);

}
