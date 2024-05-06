package com.oop.coursework.repo;

import com.oop.coursework.model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.id = :id")
    void deleteCommentById(@Param("id") Long id);

}
