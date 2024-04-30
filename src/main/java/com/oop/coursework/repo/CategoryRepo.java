package com.oop.coursework.repo;

import com.oop.coursework.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Query("SELECT cat.id, cat.name, cat.description, cat.imageUrl, " +
            "mf.id, mf.name, mf.author, mf.year, mf.description, mf.filePath, mf.fileType, mf.downloadDate, mf.downloadsNumber, mf.averageRate " +
            "FROM Category cat " +
            "JOIN cat.musicFiles mf " +
            "WHERE cat.id = :categoryId")
    List<Object[]> findCategoryById(@Param("categoryId") Long categoryId);

    @Query("SELECT cat.id, cat.name, cat.description, cat.imageUrl, " +
            "mf.id, mf.name, mf.author, mf.year, mf.description, mf.filePath, mf.fileType, mf.downloadDate, mf.downloadsNumber, mf.averageRate " +
            "FROM Category cat " +
            "JOIN cat.musicFiles mf")
    List<Object[]> findAllCategories();

}
