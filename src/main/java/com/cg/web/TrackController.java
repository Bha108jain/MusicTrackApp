package com.cg.web;

import com.cg.entity.Track;
import com.cg.repo.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/music/tracks")
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;

    // ✅ POST - Add Track
    @PostMapping
    public ResponseEntity<String> addTrack(@RequestBody Track track) {
        trackRepository.save(track);
        return ResponseEntity.status(201).body("Track Added");
    }

    // ✅ GET - All Tracks
    @GetMapping
    public ResponseEntity<List<Track>> getTracks() {
        return ResponseEntity.ok(trackRepository.findAll());
    }

    // ✅ GET - Search by Title
    @GetMapping("/search")
    public ResponseEntity<List<Track>> getTracksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(trackRepository.findByTitle(title));
    }

    // ✅ GET - Track by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTrack(@PathVariable Long id) {
        Optional<Track> track = trackRepository.findById(id);

        if (track.isPresent()) {
            return ResponseEntity.ok(track.get());
        } else {
            return ResponseEntity.status(404).body("Track not found");
        }
    }
}