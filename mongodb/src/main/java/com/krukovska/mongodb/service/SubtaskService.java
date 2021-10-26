package com.krukovska.mongodb.service;

import com.krukovska.mongodb.model.Subtask;

public interface SubtaskService {

    Subtask save(Subtask subtask);

    void deleteAll();
}
