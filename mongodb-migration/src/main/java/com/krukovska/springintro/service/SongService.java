package com.krukovska.springintro.service;

import com.krukovska.springintro.model.Song;

public interface SongService {

    Song findById(String id);

    Song create(Song song);

    Song update(Song song);

    boolean delete(String id);

}
