package com.data.session14.controller;

import com.data.session14.modal.dto.res.APIResponse;
import com.data.session14.modal.entity.Movie;
import com.data.session14.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MoviePublicController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Movie>>> getAllMovies() {
        return ResponseEntity.ok(new APIResponse<>("Lấy danh sách phim thành công", movieService.getAll(), true, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Movie>> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        return ResponseEntity.ok(new APIResponse<>("Lấy phim theo ID thành công", movie, true, HttpStatus.OK));
    }
}
