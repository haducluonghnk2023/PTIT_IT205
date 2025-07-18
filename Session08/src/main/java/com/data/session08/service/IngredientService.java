package com.data.session08.service;

import com.data.session08.model.entity.Ingredient;
import com.data.session08.model.req.IngredientDTO;

import java.util.List;

public interface IngredientService {
    Ingredient createIngredient(IngredientDTO dto);
    Ingredient updateIngredient(Long id, IngredientDTO dto);
    void deleteIngredient(Long id);
    List<Ingredient> getAllIngredients();
}
