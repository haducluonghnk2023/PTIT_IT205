package com.data.session08.service.ingredient;

import com.data.session08.model.entity.Ingredient;
import com.data.session08.model.req.IngredientDTO;
import com.data.session08.repo.IngredientRepository;
import com.data.session08.service.IngredientService;
import com.data.session08.service.cloudinary.CloudinaryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public Ingredient createIngredient(IngredientDTO dto) {
        String imageUrl = cloudinaryService.uploadFile(dto.getImage());
        Ingredient ingredient = Ingredient.builder()
                .name(dto.getName())
                .stock(dto.getStock())
                .expiry(LocalDate.parse(dto.getExpiry()))
                .image(imageUrl)
                .build();
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient updateIngredient(Long id, IngredientDTO dto) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));

        ingredient.setName(dto.getName());
        ingredient.setStock(dto.getStock());
        ingredient.setExpiry(LocalDate.parse(dto.getExpiry()));

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(dto.getImage());
            ingredient.setImage(imageUrl);
        }

        return ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new EntityNotFoundException("Ingredient not found");
        }
        ingredientRepository.deleteById(id);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return (List<Ingredient>) ingredientRepository.findAll();
    }
}
