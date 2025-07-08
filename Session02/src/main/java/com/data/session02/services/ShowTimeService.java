package com.data.session02.services;

import com.data.session02.model.entity.ShowTime;
import com.data.session02.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ShowTimeService implements IService<ShowTime, Long>{
    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Override
    public ShowTime save(ShowTime entity) {
        return showTimeRepository.save(entity);
    }

    @Override
    public Optional<ShowTime> findById(Long aLong) {
        return showTimeRepository.findById(aLong);
    }

    @Override
    public List<ShowTime> findAll() {
        return showTimeRepository.findAll();
    }

    @Override
    public ShowTime update(Long aLong, ShowTime entity) {
        return showTimeRepository.save(entity);
    }

    @Override
    public void delete(Long aLong) {
        showTimeRepository.deleteById(aLong);
    }
}
