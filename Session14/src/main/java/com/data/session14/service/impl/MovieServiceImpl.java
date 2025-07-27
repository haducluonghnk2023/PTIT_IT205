package com.data.session14.service.impl;

import com.data.session14.modal.dto.req.MovieRequest;
import com.data.session14.modal.entity.Movie;
import com.data.session14.repository.MovieRepository;
import com.data.session14.service.MovieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Lấy tất cả phim
    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    // Tạo mới phim từ MovieRequest
    @Override
    public Movie createMovie(MovieRequest movieRequest) {
        Movie movie = Movie.builder()
                .title(movieRequest.getTitle())
                .description(movieRequest.getDescription())
                .duration(movieRequest.getDuration())
                .releasedDate(movieRequest.getReleaseDate())
                .build();
        return movieRepository.save(movie);
    }

    // Cập nhật phim theo id
    @Override
    public Movie updateMovie(Long id, MovieRequest movieRequest) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie Not Found with ID: " + id));

        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setDuration(movieRequest.getDuration());
        movie.setReleasedDate(movieRequest.getReleaseDate());

        return movieRepository.save(movie);
    }

    // Xóa phim theo id
    @Override
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie Not Found with ID: " + id));

        if (movie.getShowtimes() != null && !movie.getShowtimes().isEmpty()) {
            throw new IllegalStateException("Không thể xóa phim vì đã có lịch chiếu!");
        }

        movieRepository.delete(movie);
    }

    // Tìm phim theo id
    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie Not Found with ID: " + id));
    }
}
