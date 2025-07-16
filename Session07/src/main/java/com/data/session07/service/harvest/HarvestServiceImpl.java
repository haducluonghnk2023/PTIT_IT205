package com.data.session07.service.harvest;

import com.data.session07.model.entity.Harvest;
import com.data.session07.repository.HarvestRepository;
import com.data.session07.service.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HarvestServiceImpl implements HarvestService {
    @Autowired
    private HarvestRepository  harvestRepository;

    @Override
    public List<Harvest> getAllHarvests() {
        return harvestRepository.findAll();
    }

    @Override
    public Harvest getHarvestById(Long id) {
        return harvestRepository.getReferenceById(id);
    }

    @Override
    public Harvest addHarvest(Harvest harvest) {
        return harvestRepository.save(harvest);
    }

    @Override
    public double sumTotalPriceByDateRange(LocalDate start, LocalDate end) {
        return harvestRepository.sumTotalPriceByDateRange(start, end);
    }
}
