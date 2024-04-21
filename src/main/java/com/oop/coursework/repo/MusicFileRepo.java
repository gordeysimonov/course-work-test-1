package com.oop.coursework.repo;

import com.oop.coursework.model.MusicFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicFileRepo extends JpaRepository<MusicFile, Long> {
}
