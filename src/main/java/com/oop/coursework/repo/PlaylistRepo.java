package com.oop.coursework.repo;

import com.oop.coursework.annotation.LogRepository;
import com.oop.coursework.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepo extends JpaRepository<Playlist, Long> {

    @LogRepository
    @Query("SELECT pl.id, pl.name, pl.description, mf.id, mf.name, mf.author, mf.year, mf.description, mf.filePath, mf.fileType, mf.downloadDate, mf.downloadsNumber, " +
            "u.id, u.username " +
            "FROM Playlist pl " +
            "JOIN pl.userId u " +
            "JOIN pl.musicFiles mf " +
            "WHERE pl.id = :playlistId")
    List<Object[]> findPlaylistById(@Param("playlistId") Long playlistId);

    @LogRepository
    @Query("SELECT pl.id, pl.name, pl.description, mf.id, mf.name, mf.author, mf.year, mf.description, mf.filePath, mf.fileType, mf.downloadDate, mf.downloadsNumber, " +
            "u.id, u.username " +
            "FROM Playlist pl " +
            "JOIN pl.userId u " +
            "JOIN pl.musicFiles mf")
    List<Object[]> findAllPlaylists();

    @LogRepository
    @Query("SELECT pl.id, pl.name, pl.description, mf.id, mf.name, mf.author, mf.year, mf.description, mf.filePath, mf.fileType, mf.downloadDate, mf.downloadsNumber, " +
            "u.id, u.username " +
            "FROM Playlist pl " +
            "JOIN pl.userId u " +
            "JOIN pl.musicFiles mf " +
            "WHERE pl.name LIKE CONCAT('%', :name, '%') AND pl.userId.id = :id")
    List<Object[]> findByNameAndUser(@Param("name") String name, @Param("id") Long id);

    @LogRepository
    @Query("SELECT pl.id, pl.name, pl.description, mf.id, mf.name, mf.author, mf.year, mf.description, mf.filePath, mf.fileType, mf.downloadDate, mf.downloadsNumber, " +
            "u.id, u.username " +
            "FROM Playlist pl " +
            "JOIN pl.userId u " +
            "JOIN pl.musicFiles mf " +
            "WHERE pl.userId.id = :id")
    List<Object[]> findAllPlaylists(@Param("id") Long id);

}
