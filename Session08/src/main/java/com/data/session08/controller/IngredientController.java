package com.data.session08.controller;

import com.data.session08.model.entity.Ingredient;
import com.data.session08.model.req.IngredientDTO;
import com.data.session08.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    @Autowired
    private  IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<Ingredient> create(@ModelAttribute IngredientDTO dto) {
        return ResponseEntity.ok(ingredientService.createIngredient(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable Long id, @ModelAttribute IngredientDTO dto) {
        return ResponseEntity.ok(ingredientService.updateIngredient(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAll() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }
}