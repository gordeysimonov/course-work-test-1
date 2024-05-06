package com.oop.coursework.repo;

import com.oop.coursework.model.Genre;
import com.oop.coursework.model.MusicFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MusicFileRepo extends JpaRepository<MusicFile, Long> {

    @Query("SELECT json_build_object(" +
            "'id', mf.id, " +
            "'name', mf.name, " +
            "'author', mf.author, " +
            "'year', mf.year, " +
            "'description', mf.description, " +
            "'lyrics', mf.lyrics, " +
            "'filePath', mf.filePath, " +
            "'fileType', mf.fileType, " +
            "'downloadDate', mf.downloadDate, " +
            "'downloadsNumber', mf.downloadsNumber, " +
            "'tags', (SELECT json_agg(json_build_object('id', t.id, 'tagName', t.tagName)) FROM mf.tags t), " +
            "'genres', (SELECT json_agg(json_build_object('id', g.id, 'genre', g.genre)) FROM mf.genres g), " +
            "'users', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM mf.userId u), " +
            "'commentsCount', (SELECT COUNT(*) FROM mf.musicFileCommentList), " +
            "'comments', (SELECT json_agg(json_build_object('id', c.id, 'comment', c.commentText)) FROM mf.musicFileCommentList c WHERE c.reply IS NULL), " +
            "'averageRate', mf.averageRate, " +
            "'ratings', (SELECT json_agg(json_build_object('id', r.id, 'rating', r.rate)) FROM mf.musicFileRatingList r), " +
            "'categories', (SELECT json_agg(json_build_object('id', cat.id, 'categoryName', cat.name)) FROM mf.categories cat), " +
            "'playlists', (SELECT json_agg(json_build_object('id', p.id, 'playlistName', p.name)) FROM mf.playlists p) " +
            ") " +
            "FROM MusicFile mf")
    List<Object[]> findAllMusicFiles();

    @Query("SELECT json_build_object(" +
            "'id', mf.id, " +
            "'name', mf.name, " +
            "'author', mf.author, " +
            "'year', mf.year, " +
            "'description', mf.description, " +
            "'lyrics', mf.lyrics, " +
            "'filePath', mf.filePath, " +
            "'fileType', mf.fileType, " +
            "'downloadDate', mf.downloadDate, " +
            "'downloadsNumber', mf.downloadsNumber, " +
            "'tags', (SELECT json_agg(json_build_object('id', t.id, 'tagName', t.tagName)) FROM mf.tags t), " +
            "'genres', (SELECT json_agg(json_build_object('id', g.id, 'genre', g.genre)) FROM mf.genres g), " +
            "'users', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM mf.userId u), " +
            "'commentsCount', (SELECT COUNT(*) FROM mf.musicFileCommentList), " +
            "'comments', (SELECT json_agg(json_build_object('id', c.id, 'comment', c.commentText)) FROM mf.musicFileCommentList c WHERE c.reply IS NULL), " +
            "'averageRate', mf.averageRate, " +
            "'ratings', (SELECT json_agg(json_build_object('id', r.id, 'rating', r.rate)) FROM mf.musicFileRatingList r), " +
            "'categories', (SELECT json_agg(json_build_object('id', cat.id, 'categoryName', cat.name)) FROM mf.categories cat), " +
            "'playlists', (SELECT json_agg(json_build_object('id', p.id, 'playlistName', p.name)) FROM mf.playlists p) " +
            ") " +
            "FROM MusicFile mf WHERE mf.id = :id")
    List<Object[]> findMusicFileById(@Param("id") Long id);

    @Query("SELECT json_build_object(" +
            "'id', mf.id, " +
            "'name', mf.name, " +
            "'author', mf.author, " +
            "'year', mf.year, " +
            "'description', mf.description, " +
            "'lyrics', mf.lyrics, " +
            "'filePath', mf.filePath, " +
            "'fileType', mf.fileType, " +
            "'downloadDate', mf.downloadDate, " +
            "'downloadsNumber', mf.downloadsNumber, " +
            "'tags', (SELECT json_agg(json_build_object('id', t.id, 'tagName', t.tagName)) FROM mf.tags t), " +
            "'genres', (SELECT json_agg(json_build_object('id', g.id, 'genre', g.genre)) FROM mf.genres g), " +
            "'users', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM mf.userId u), " +
            "'commentsCount', (SELECT COUNT(*) FROM mf.musicFileCommentList), " +
            "'comments', (SELECT json_agg(json_build_object('id', c.id, 'comment', c.commentText)) FROM mf.musicFileCommentList c WHERE c.reply IS NULL), " +
            "'averageRate', mf.averageRate, " +
            "'ratings', (SELECT json_agg(json_build_object('id', r.id, 'rating', r.rate)) FROM mf.musicFileRatingList r), " +
            "'categories', (SELECT json_agg(json_build_object('id', cat.id, 'categoryName', cat.name)) FROM mf.categories cat), " +
            "'playlists', (SELECT json_agg(json_build_object('id', p.id, 'playlistName', p.name)) FROM mf.playlists p) " +
            ") " +
            "FROM MusicFile mf WHERE mf.userId.id = :userId")
    List<Object[]> findMusicFilesByUserId(@Param("userId") Long userId);

    @Query("SELECT json_build_object(" +
            "'id', mf.id, " +
            "'name', mf.name, " +
            "'author', mf.author, " +
            "'year', mf.year, " +
            "'description', mf.description, " +
            "'lyrics', mf.lyrics, " +
            "'filePath', mf.filePath, " +
            "'fileType', mf.fileType, " +
            "'downloadDate', mf.downloadDate, " +
            "'downloadsNumber', mf.downloadsNumber, " +
            "'tags', (SELECT json_agg(json_build_object('id', t.id, 'tagName', t.tagName)) FROM mf.tags t WHERE t.tagName IN :tagNames), " +
            "'genres', (SELECT json_agg(json_build_object('id', g.id, 'genre', g.genre)) FROM mf.genres g), " +
            "'users', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM mf.userId u), " +
            "'commentsCount', (SELECT COUNT(*) FROM mf.musicFileCommentList), " +
            "'comments', (SELECT json_agg(json_build_object('id', c.id, 'comment', c.commentText)) FROM mf.musicFileCommentList c WHERE c.reply IS NULL), " +
            "'averageRate', mf.averageRate, " +
            "'ratings', (SELECT json_agg(json_build_object('id', r.id, 'rating', r.rate)) FROM mf.musicFileRatingList r), " +
            "'categories', (SELECT json_agg(json_build_object('id', cat.id, 'categoryName', cat.name)) FROM mf.categories cat), " +
            "'playlists', (SELECT json_agg(json_build_object('id', p.id, 'playlistName', p.name)) FROM mf.playlists p) " +
            ") " +
            "FROM MusicFile mf JOIN mf.tags t " +
            "WHERE t.tagName IN :tagNames " +
            "GROUP BY mf.id " +
            "HAVING COUNT(DISTINCT t.tagName) = :tagCount")
    List<Object[]> findByTags(@Param("tagNames") List<String> tagNames, @Param("tagCount") int tagCount);

    @Query("SELECT json_build_object(" +
            "'id', mf.id, " +
            "'name', mf.name, " +
            "'author', mf.author, " +
            "'year', mf.year, " +
            "'description', mf.description, " +
            "'lyrics', mf.lyrics, " +
            "'filePath', mf.filePath, " +
            "'fileType', mf.fileType, " +
            "'downloadDate', mf.downloadDate, " +
            "'downloadsNumber', mf.downloadsNumber, " +
            "'tags', (SELECT json_agg(json_build_object('id', t.id, 'tagName', t.tagName)) FROM mf.tags t), " +
            "'genres', (SELECT json_agg(json_build_object('id', g.id, 'genre', g.genre)) FROM mf.genres g WHERE g.genre IN :genreNames), " +
            "'users', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM mf.userId u), " +
            "'commentsCount', (SELECT COUNT(*) FROM mf.musicFileCommentList), " +
            "'comments', (SELECT json_agg(json_build_object('id', c.id, 'comment', c.commentText)) FROM mf.musicFileCommentList c WHERE c.reply IS NULL), " +
            "'averageRate', mf.averageRate, " +
            "'ratings', (SELECT json_agg(json_build_object('id', r.id, 'rating', r.rate)) FROM mf.musicFileRatingList r), " +
            "'categories', (SELECT json_agg(json_build_object('id', cat.id, 'categoryName', cat.name)) FROM mf.categories cat), " +
            "'playlists', (SELECT json_agg(json_build_object('id', p.id, 'playlistName', p.name)) FROM mf.playlists p) " +
            ") " +
            "FROM MusicFile mf JOIN mf.genres g " +
            "WHERE g.genre IN :genreNames " +
            "GROUP BY mf.id " +
            "HAVING COUNT(DISTINCT g.genre) = :genreCount")
    List<Object[]> findByGenres(@Param("genreNames") List<String> genreNames, @Param("genreCount") int genreCount);

    @Query("SELECT json_build_object(" +
            "'id', mf.id, " +
            "'name', mf.name, " +
            "'author', mf.author, " +
            "'year', mf.year, " +
            "'description', mf.description, " +
            "'lyrics', mf.lyrics, " +
            "'filePath', mf.filePath, " +
            "'fileType', mf.fileType, " +
            "'downloadDate', mf.downloadDate, " +
            "'downloadsNumber', mf.downloadsNumber, " +
            "'tags', (SELECT json_agg(json_build_object('id', t.id, 'tagName', t.tagName)) FROM mf.tags t), " +
            "'genres', (SELECT json_agg(json_build_object('id', g.id, 'genre', g.genre)) FROM mf.genres g), " +
            "'users', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM mf.userId u), " +
            "'commentsCount', (SELECT COUNT(*) FROM mf.musicFileCommentList), " +
            "'comments', (SELECT json_agg(json_build_object('id', c.id, 'comment', c.commentText)) FROM mf.musicFileCommentList c WHERE c.reply IS NULL), " +
            "'averageRate', mf.averageRate, " +
            "'ratings', (SELECT json_agg(json_build_object('id', r.id, 'rating', r.rate)) FROM mf.musicFileRatingList r), " +
            "'categories', (SELECT json_agg(json_build_object('id', cat.id, 'categoryName', cat.name)) FROM mf.categories cat), " +
            "'playlists', (SELECT json_agg(json_build_object('id', p.id, 'playlistName', p.name)) FROM mf.playlists p) " +
            ") " +
            "FROM MusicFile mf WHERE mf.name LIKE CONCAT('%', :name, '%') ORDER BY mf.averageRate DESC")
    List<Object[]> findByName(@Param("name") String name);

    @Query("SELECT json_build_object(" +
            "'id', mf.id, " +
            "'name', mf.name, " +
            "'author', mf.author, " +
            "'year', mf.year, " +
            "'description', mf.description, " +
            "'lyrics', mf.lyrics, " +
            "'filePath', mf.filePath, " +
            "'fileType', mf.fileType, " +
            "'downloadDate', mf.downloadDate, " +
            "'downloadsNumber', mf.downloadsNumber, " +
            "'tags', (SELECT json_agg(json_build_object('id', t.id, 'tagName', t.tagName)) FROM mf.tags t), " +
            "'genres', (SELECT json_agg(json_build_object('id', g.id, 'genre', g.genre)) FROM mf.genres g), " +
            "'users', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM mf.userId u), " +
            "'commentsCount', (SELECT COUNT(*) FROM mf.musicFileCommentList), " +
            "'comments', (SELECT json_agg(json_build_object('id', c.id, 'comment', c.commentText)) FROM mf.musicFileCommentList c WHERE c.reply IS NULL), " +
            "'averageRate', mf.averageRate, " +
            "'ratings', (SELECT json_agg(json_build_object('id', r.id, 'rating', r.rate)) FROM mf.musicFileRatingList r), " +
            "'categories', (SELECT json_agg(json_build_object('id', cat.id, 'categoryName', cat.name)) FROM mf.categories cat), " +
            "'playlists', (SELECT json_agg(json_build_object('id', p.id, 'playlistName', p.name)) FROM mf.playlists p) " +
            ") " +
            "FROM MusicFile mf WHERE mf.author LIKE CONCAT('%', :author, '%') ORDER BY mf.averageRate DESC")
    List<Object[]> findByAuthor(@Param("author") String author);

    @Query("SELECT json_build_object(" +
            "'id', mf.id, " +
            "'name', mf.name, " +
            "'author', mf.author, " +
            "'year', mf.year, " +
            "'description', mf.description, " +
            "'lyrics', mf.lyrics, " +
            "'filePath', mf.filePath, " +
            "'fileType', mf.fileType, " +
            "'downloadDate', mf.downloadDate, " +
            "'downloadsNumber', mf.downloadsNumber, " +
            "'tags', (SELECT json_agg(json_build_object('id', t.id, 'tagName', t.tagName)) FROM mf.tags t), " +
            "'genres', (SELECT json_agg(json_build_object('id', g.id, 'genre', g.genre)) FROM mf.genres g), " +
            "'users', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM mf.userId u), " +
            "'commentsCount', (SELECT COUNT(*) FROM mf.musicFileCommentList), " +
            "'comments', (SELECT json_agg(json_build_object('id', c.id, 'comment', c.commentText)) FROM mf.musicFileCommentList c WHERE c.reply IS NULL), " +
            "'averageRate', mf.averageRate, " +
            "'ratings', (SELECT json_agg(json_build_object('id', r.id, 'rating', r.rate)) FROM mf.musicFileRatingList r), " +
            "'categories', (SELECT json_agg(json_build_object('id', cat.id, 'categoryName', cat.name)) FROM mf.categories cat), " +
            "'playlists', (SELECT json_agg(json_build_object('id', p.id, 'playlistName', p.name)) FROM mf.playlists p) " +
            ") " +
            "FROM MusicFile mf WHERE mf.year = :year ORDER BY mf.averageRate DESC")
    List<Object[]> findByYear(@Param("year") int year);

    Optional<MusicFile> findById(long id);
    void deleteById(long id);
    List<MusicFile> findByGenresContains(Genre genre);

}
