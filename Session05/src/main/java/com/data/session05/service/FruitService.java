package com.data.session05.service;

import com.data.session05.modal.dto.res.FruitDto;
import com.data.session05.modal.entity.Fruit;

import java.util.List;

public interface FruitService {
    List<FruitDto> getAllFruits();
    Fruit getFruitById(Long id);
    Fruit createFruit(Fruit fruit);
    Fruit updateFruit(Long id, Fruit fruit);
    void deleteFruit(Long id);
}
