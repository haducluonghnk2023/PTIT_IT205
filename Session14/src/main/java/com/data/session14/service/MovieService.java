package com.data.session14.service;

import com.data.session14.modal.dto.req.MovieRequest;
import com.data.session14.modal.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    Movie createMovie(MovieRequest movieRequest);
    Movie updateMovie(Long id, MovieRequest movieRequest);
    void deleteMovie(Long id);
    Movie findById(Long id);
}
