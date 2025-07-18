package com.data.session08.service.dish;

import com.data.session08.model.entity.Dish;
import com.data.session08.model.req.DishDTO;
import com.data.session08.repo.DishRepository;
import com.data.session08.service.DishService;
import com.data.session08.service.cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public Dish createDish(DishDTO dto) {
        String imageUrl = cloudinaryService.uploadFile(dto.getImage());
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setPrice(dto.getPrice());
        dish.setStatus(dto.getStatus());
        dish.setImage(imageUrl);
        return dishRepository.save(dish);
    }

    @Override
    public Dish updateDish(Long id, DishDTO dto) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Dish not found"));

        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setPrice(dto.getPrice());
        dish.setStatus(dto.getStatus());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(dto.getImage());
            dish.setImage(imageUrl);
        }

        return dishRepository.save(dish);
    }

    @Override
    public void deleteDish(Long id) {
        if (!dishRepository.existsById(id)) {
            throw new NoSuchElementException("Dish not found");
        }
        dishRepository.deleteById(id);
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }
}
