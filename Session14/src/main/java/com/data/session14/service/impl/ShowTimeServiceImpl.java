package com.data.session14.service.impl;

import com.data.session14.modal.dto.req.ShowTimeRequest;
import com.data.session14.modal.entity.Movie;
import com.data.session14.modal.entity.ShowTime;
import com.data.session14.repository.MovieRepository;
import com.data.session14.repository.ShowTimeRepository;
import com.data.session14.service.ShowTimeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShowTimeServiceImpl implements ShowTimeService {
    @Autowired
    private ShowTimeRepository showTimeRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<ShowTime> getShowTimes() {
        return showTimeRepository.findAll();
    }

    @Override
    public ShowTime createShowTime(ShowTimeRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phim"));
        ShowTime st = ShowTime.builder()
                .movie(movie)
                .startTime(request.getStartTime())
                .room(request.getRoom())
                .build();
        return showTimeRepository.save(st);
    }
}
