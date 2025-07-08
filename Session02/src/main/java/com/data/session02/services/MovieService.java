package com.data.session02.services;

import com.data.session02.model.entity.Movie;
import com.data.session02.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MovieService implements  IService<Movie, Long> {
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public Movie save(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public Optional<Movie> findById(Long aLong) {
        return movieRepository.findById(aLong);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie update(Long aLong, Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public void delete(Long aLong) {
        movieRepository.deleteById(aLong);
    }
}
