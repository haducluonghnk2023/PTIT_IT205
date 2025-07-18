package com.data.session08.service;

import com.data.session08.model.entity.Dish;
import com.data.session08.model.req.DishDTO;

import java.util.List;

public interface DishService {
    Dish createDish(DishDTO dto);
    Dish updateDish(Long id, DishDTO dto);
    void deleteDish(Long id);
    List<Dish> getAllDishes();
}
