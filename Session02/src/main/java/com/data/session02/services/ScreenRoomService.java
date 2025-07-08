package com.data.session02.services;

import com.data.session02.model.entity.ScreenRoom;
import com.data.session02.repository.ScreenRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenRoomService implements  IService<ScreenRoom, Long> {
    @Autowired
    private ScreenRoomRepository screenRoomRepository;
    @Override
    public ScreenRoom save(ScreenRoom entity) {
        return screenRoomRepository.save(entity);
    }

    @Override
    public Optional<ScreenRoom> findById(Long aLong) {
        return screenRoomRepository.findById(aLong);
    }

    @Override
    public List<ScreenRoom> findAll() {
        return screenRoomRepository.findAll();
    }

    @Override
    public ScreenRoom update(Long aLong, ScreenRoom entity) {
        return screenRoomRepository.save(entity);
    }

    @Override
    public void delete(Long aLong) {
        screenRoomRepository.deleteById(aLong);
    }
}
