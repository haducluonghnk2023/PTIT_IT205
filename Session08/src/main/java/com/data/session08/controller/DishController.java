package com.data.session08.controller;

import com.data.session08.model.entity.Dish;
import com.data.session08.model.req.DishDTO;
import com.data.session08.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    public ResponseEntity<?> createDish(@ModelAttribute DishDTO dto) {
        Dish created = dishService.createDish(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDish(@PathVariable Long id, @ModelAttribute DishDTO dto) {
        Dish updated = dishService.updateDish(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes() {
        return ResponseEntity.ok(dishService.getAllDishes());
    }
}

