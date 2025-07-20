package com.data.session09.controller;


import com.data.session09.model.dto.SearchKeywordStats;
import com.data.session09.model.entity.Movie;
import com.data.session09.repo.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(@RequestParam(required = false) String searchMovie) {
        long startTime = System.currentTimeMillis();

        List<Movie> movies;
        if (searchMovie != null && !searchMovie.isBlank()) {
            movies = movieRepository.findByTitleContainingIgnoreCase(searchMovie);
        } else {
            movies = movieRepository.findAll();
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        String GREEN = "\u001B[32m";
        String RESET = "\u001B[0m";

        logger.info(GREEN + "[GET /movies] searchMovie = " + searchMovie +
                " | total = " + movies.size() +
                " | duration = " + duration + "ms" + RESET);

        return ResponseEntity.ok(movies);
    }


    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
        try {
            Movie saved = movieRepository.save(movie);
            logger.info("Thêm phim thành công: {} lúc {}", saved.getTitle(), LocalDateTime.now());
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            logger.error("Lỗi khi thêm phim: " + e.getMessage(), e);
            return ResponseEntity.status(500).body("Đã xảy ra lỗi khi thêm phim.");
        }
    }

    // PUT /movies/{id} - Cập nhật phim
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        try {
            Optional<Movie> optionalMovie = movieRepository.findById(id);
            if (optionalMovie.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Movie oldMovie = optionalMovie.get();
            Movie beforeUpdate = new Movie(); // Tạo bản sao thông tin cũ
            beforeUpdate.setId(oldMovie.getId());
            beforeUpdate.setTitle(oldMovie.getTitle());
            beforeUpdate.setDescription(oldMovie.getDescription());
            beforeUpdate.setReleaseDate(oldMovie.getReleaseDate());
            beforeUpdate.setPoster(oldMovie.getPoster());

            // Cập nhật thông tin
            oldMovie.setTitle(updatedMovie.getTitle());
            oldMovie.setDescription(updatedMovie.getDescription());
            oldMovie.setReleaseDate(updatedMovie.getReleaseDate());
            oldMovie.setPoster(updatedMovie.getPoster());

            Movie saved = movieRepository.save(oldMovie);

            logger.info(YELLOW + "Thông tin cũ: " + beforeUpdate + RESET);
            logger.info(GREEN + "Thông tin mới: " + saved + RESET);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            logger.error(RED + "Lỗi khi cập nhật phim: " + e.getMessage() + RESET, e);
            return ResponseEntity.status(500).body("Lỗi khi cập nhật phim.");
        }
    }

    // DELETE /movies/{id} - Xóa phim
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        try {
            Optional<Movie> optionalMovie = movieRepository.findById(id);
            if (optionalMovie.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Movie movieToDelete = optionalMovie.get();
            movieRepository.deleteById(id);

            logger.info(RED + "Xóa thành công" + RESET);
            logger.info(GREEN + movieToDelete.toString() + RESET);
            return ResponseEntity.ok("Đã xóa phim.");
        } catch (Exception e) {
            logger.error(RED + "Lỗi khi xóa phim: " + e.getMessage() + RESET, e);
            return ResponseEntity.status(500).body("Lỗi khi xóa phim.");
        }
    }

    @GetMapping("/search-logs")
    public ResponseEntity<List<SearchKeywordStats>> getSearchLogs() {
        Map<String, Integer> keywordCount = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("logs/app.log"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Search keyword:")) {
                    int start = line.indexOf("Search keyword:") + "Search keyword:".length();
                    String keyword = line.substring(start).trim().replaceAll("\"", "");
                    keywordCount.put(keyword, keywordCount.getOrDefault(keyword, 0) + 1);
                }
            }
        } catch (IOException e) {
            logger.error("Error reading log file", e);
            return ResponseEntity.status(500).build();
        }

        List<SearchKeywordStats> result = keywordCount.entrySet().stream()
                .map(entry -> new SearchKeywordStats(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<Movie>> getSuggestions() {
        Set<String> keywords = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("logs/app.log"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Search keyword:")) {
                    int start = line.indexOf("Search keyword:") + "Search keyword:".length();
                    String keyword = line.substring(start).trim().replaceAll("\"", "");
                    keywords.add(keyword);
                }
            }
        } catch (IOException e) {
            logger.error("Error reading log file", e);
            return ResponseEntity.status(500).build();
        }

        Set<Movie> suggestedMovies = new HashSet<>();
        for (String keyword : keywords) {
            List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(keyword);
            suggestedMovies.addAll(movies);
        }

        return ResponseEntity.ok(new ArrayList<>(suggestedMovies));
    }
}
