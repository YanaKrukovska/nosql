package com.krukovska.springintro.service.impl;

import com.krukovska.springintro.model.Song;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SongServiceImplTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private SongServiceImpl service;

    @Test
    void findByIdExists() {
        Song expected = new Song("55b5ffa5511fee0e45ed614b", "Champagne Problems", "Taylor Swift", 2020);
        when(mongoTemplate.findById(new ObjectId("55b5ffa5511fee0e45ed614b"), Song.class)).thenReturn(expected);
        assertEquals(expected, service.findById("55b5ffa5511fee0e45ed614b"));
    }

    @Test
    void findByIdNotExists() {
        when(mongoTemplate.findById(new ObjectId("55b5ffa5511fee0e45ed614b"), Song.class)).thenReturn(null);
        assertNull(service.findById("55b5ffa5511fee0e45ed614b"));
    }

    @Test
    void createNewSong() {
        Song newSong = new Song("If", "Bread", 1972);
        Song expected = new Song("55b5ffa5511fee0e45ed614b", "If", "Bread", 1972);
        when(mongoTemplate.insert(newSong, "song")).thenReturn(expected);
        assertEquals(expected, service.create(newSong));
    }

    @Test
    void updateExistingSong() {
        Song existingSong = new Song("55b5ffa5511fee0e45ed614b", "Honeymoon", "Lana Del Ray", 2013);
        Song expected = new Song("55b5ffa5511fee0e45ed614b", "Honeymoon", "Lana Del Rey", 2015);
        when(mongoTemplate.save(existingSong, "song")).thenReturn(expected);
        assertEquals(expected, service.update(existingSong));
    }

    @Test
    void updateNotExistingSong() {
        assertNull(service.update(new Song("11b3ffa5511fee0e45ed614b", "Pretty Venom", "All Time Low", 2020)));
    }

    @Test
    void deleteExistingSong() {
        Song song = new Song("55b5ffa5511fee0e45ed614b", "Take Me Home, Country Roads", "John Denver", 1971);
        when(mongoTemplate.findById(new ObjectId("55b5ffa5511fee0e45ed614b"), Song.class)).thenReturn(song).thenReturn(null);
        assertTrue(service.delete("55b5ffa5511fee0e45ed614b"));
    }

    @Test
    void deleteNotExistingSong() {
        when(mongoTemplate.findById(new ObjectId("55b5ffa5511fee0e45ed614b"), Song.class)).thenReturn(null);
        assertFalse(service.delete("55b5ffa5511fee0e45ed614b"));
    }
}