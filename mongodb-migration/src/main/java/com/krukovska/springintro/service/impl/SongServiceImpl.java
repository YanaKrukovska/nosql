package com.krukovska.springintro.service.impl;

import com.krukovska.springintro.model.Song;
import com.krukovska.springintro.service.SongService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public SongServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Song findById(String id) {
        return mongoTemplate.findById(new ObjectId(String.valueOf(id)), Song.class);
    }

    @Override
    public Song create(Song song) {
        return mongoTemplate.insert(song, "song");
    }

    @Override
    public Song update(Song song) {
        return mongoTemplate.save(song, "song");
    }

    @Override
    public boolean delete(String id) {
        Song song = mongoTemplate.findById(new ObjectId(String.valueOf(id)), Song.class);
        if (song == null) {
            return false;
        }

        mongoTemplate.remove(song);
        return mongoTemplate.findById(new ObjectId(String.valueOf(id)), Song.class) == null;
    }
}
