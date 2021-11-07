package com.krukovska.mongodb.service;

import com.krukovska.mongodb.model.Subtask;
import com.krukovska.mongodb.model.Task;

import java.util.List;

public interface TaskService {

    Task findById(String id);

    Task save(Task task);

    void delete(String id);

    void deleteAll();

    List<Task> findAllTasks();

    List<Task> findAllOverdueTasks();

    List<Task> findAllTasksByCategory(String category);

    List<Subtask> findAllSubtasksByCategory(String mentoring);

    List<Task> findAllByDescription(String word);

}
