package com.cg.repo;

import com.cg.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Long> {

    // Custom query method
    List<Track> findByTitle(String title);
}