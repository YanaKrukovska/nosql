package com.krukovska.mongodb.repository;

import com.krukovska.mongodb.model.Subtask;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubtaskRepository extends MongoRepository<Subtask, String> {
}
