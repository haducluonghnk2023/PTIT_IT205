package com.data.session02.repository;

import com.data.session02.model.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
    List<ShowTime> findByMovie_Id(Long movieId);
    List<ShowTime> findByScreenRoom_Id(Long screenRoomId);
}

