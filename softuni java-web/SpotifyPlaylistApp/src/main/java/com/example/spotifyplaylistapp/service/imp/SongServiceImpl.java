package com.example.spotifyplaylistapp.service.imp;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import com.example.spotifyplaylistapp.repository.SongRepo;
import com.example.spotifyplaylistapp.service.SongService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepo songRepo;

    public SongServiceImpl(SongRepo songRepo) {
        this.songRepo = songRepo;
    }

    @Override
    public void addSong(Song song) {
        songRepo.save(song);
    }

    @Override
    public Set<Song> getSongsFromCurrentUser(Long id) {
        return songRepo.findAllByUser_Id(id);
    }

    @Override
    public Set<Song> getAllPopSongs(StyleEnum styleName) {
        return songRepo.findAllByStyle_Name(styleName);
    }

    @Override
    public Integer totalTime(Long id) {
        return songRepo.totalTime(id);
    }

    @Override
    public void removeAll(Long id) {
        songRepo.deleteAllByUser_Id(id);
    }
}
