package com.oop.coursework.repo;

import com.oop.coursework.annotation.LogRepository;
import com.oop.coursework.model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommentRepo extends JpaRepository<Comment, Long> {

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', c.id, " +
            "'comment', c.commentText, " +
            "'postDate', c.postDate, " +
            "'replyId', c.reply.id, " +
            "'userId', c.userId.id, " +
            "'musicFileId', c.musicFileId.id " +
            ") " +
            "FROM Comment c WHERE c.id = :id")
    List<Object[]> findCommentById(@Param("id") Long id);

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', c.id, " +
            "'comment', c.commentText, " +
            "'postDate', c.postDate, " +
            "'replyId', c.reply.id, " +
            "'userId', c.userId.id, " +
            "'musicFileId', c.musicFileId.id " +
            ") " +
            "FROM Comment c WHERE c.musicFileId.id = :id")
    List<Object[]> findCommentsByFileId(@Param("id") Long id);

    @LogRepository
    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.id = :id")
    void deleteCommentById(@Param("id") Long id);

}
