package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;

import java.util.Set;

public interface SongService {
    void addSong(Song song);

    Set<Song> getSongsFromCurrentUser(Long id);

    Set<Song> getAllPopSongs(StyleEnum styleName);

    Integer totalTime(Long id);


    void removeAll(Long id);
}
