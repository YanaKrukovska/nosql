package com.krukovska.mongodb.service.impl;

import com.krukovska.mongodb.model.Subtask;
import com.krukovska.mongodb.repository.SubtaskRepository;
import com.krukovska.mongodb.service.SubtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubtaskServiceImpl implements SubtaskService {

    private final SubtaskRepository repository;

    @Autowired
    public SubtaskServiceImpl(SubtaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subtask save(Subtask subtask) {
        return repository.save(subtask);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
