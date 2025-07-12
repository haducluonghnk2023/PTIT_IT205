package com.data.session04.services.impl;

import com.data.session04.model.entity.FoodItem;
import com.data.session04.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;

    public Page<FoodItem> search(String name, Long categoryId, Pageable pageable) {
        if (categoryId != null) {
            return foodItemRepository.findByNameContainingIgnoreCaseAndCategory_Id(name, categoryId, pageable);
        }
        return foodItemRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    public FoodItem getById(Long id) {
        return foodItemRepository.findById(id).orElse(null);
    }

    public void save(FoodItem foodItem) {
        foodItemRepository.save(foodItem);
    }

    public void delete(Long id) {
        foodItemRepository.deleteById(id);
    }
}
