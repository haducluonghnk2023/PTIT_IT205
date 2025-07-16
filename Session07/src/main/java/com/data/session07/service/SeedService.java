package com.data.session07.service;

import com.data.session07.model.entity.Seed;

import java.util.List;

public interface SeedService {
    List<Seed> getAllSeeds();
    Seed getSeedById(Long id);
    Seed addSeed(Seed seed);
    Seed updateSeed(Long id, Seed seed);
    void deleteSeed(Long id);
    int sumAllQuantity();
}
