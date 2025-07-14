package com.data.session05.service.fruit;

import com.data.session05.modal.dto.res.FruitDto;
import com.data.session05.modal.entity.Fruit;
import com.data.session05.repo.FruitRepository;
import com.data.session05.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FruitServiceImpl implements FruitService {
    @Autowired
    private FruitRepository fruitRepository;
    @Override
    public List<FruitDto> getAllFruits() {
        return fruitRepository.findAll().stream()
                .map(f -> new FruitDto(f.getId(), f.getName(), f.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Fruit getFruitById(Long id) {
        return fruitRepository.findById(id).orElse(null);
    }

    @Override
    public Fruit createFruit(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    @Override
    public Fruit updateFruit(Long id, Fruit fruit) {
        Optional<Fruit> optionalFruit = fruitRepository.findById(id);
        if (optionalFruit.isPresent()) {
            Fruit f = optionalFruit.get();
            f.setName(fruit.getName());
            f.setPrice(fruit.getPrice());
            f.setStock(fruit.getStock());
            f.setStatus(fruit.getStatus());
            f.setCreatedAt(fruit.getCreatedAt());
            return fruitRepository.save(f);
        }
        return null;
    }

    @Override
    public void deleteFruit(Long id) {
        fruitRepository.deleteById(id);
    }
}
