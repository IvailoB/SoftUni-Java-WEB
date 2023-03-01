package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SongRepo extends JpaRepository<Song, Long> {

    Set<Song> findAllByUser_Id(Long id);

    Set<Song> findAllByStyle_Name(StyleEnum styleName);

    @Query("SELECT sum(s.duration) from Song s where s.user.id = :id")
    Integer totalTime(Long id);

    void deleteAllByUser_Id(Long id);
}
