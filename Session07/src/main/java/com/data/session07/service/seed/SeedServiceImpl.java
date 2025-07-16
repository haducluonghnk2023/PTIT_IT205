package com.data.session07.service.seed;

import com.data.session07.model.entity.Seed;
import com.data.session07.repository.SeedRepository;
import com.data.session07.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeedServiceImpl implements SeedService {
    @Autowired
    private SeedRepository seedRepository;
    @Override
    public List<Seed> getAllSeeds() {
        return seedRepository.findAll();
    }

    @Override
    public Seed getSeedById(Long id) {
        return seedRepository.findById(id).orElse(null);
    }

    @Override
    public Seed addSeed(Seed seed) {
        return seedRepository.save(seed);
    }

    @Override
    public Seed updateSeed(Long id, Seed seed) {
        Optional<Seed> optional = seedRepository.findById(id);
        if (optional.isPresent()) {
            Seed old = optional.get();
            old.setName(seed.getName());
            old.setQuantity(seed.getQuantity());
            return seedRepository.save(old);
        }
        return null;
    }

    @Override
    public void deleteSeed(Long id) {
        seedRepository.deleteById(id);
    }

    @Override
    public int sumAllQuantity() {
        return seedRepository.sumAllQuantity();
    }
}
