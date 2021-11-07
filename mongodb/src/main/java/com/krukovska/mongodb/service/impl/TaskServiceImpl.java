package com.krukovska.mongodb.service.impl;

import com.krukovska.mongodb.model.Subtask;
import com.krukovska.mongodb.model.Task;
import com.krukovska.mongodb.repository.TaskRepository;
import com.krukovska.mongodb.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task findById(String id) {
        return repository.findTaskById(id);
    }

    @Override
    public Task save(Task task) {
        return repository.save(task);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<Task> findAllTasks() {
        return repository.findAll();
    }

    @Override
    public List<Task> findAllTasksByCategory(String category) {
        return repository.findAllCategoryTasks(category);
    }

    @Override
    public List<Subtask> findAllSubtasksByCategory(String category) {
        return repository.findAllCategoryTasks(category)
                .stream()
                .map(Task::getSubtasks)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> findAllByDescription(String word) {
        return repository.findAllByDescriptionContains(word);
    }


    @Override
    public List<Task> findAllOverdueTasks() {
        return repository.findAllByDeadlineBefore(LocalDateTime.now());
    }
}
