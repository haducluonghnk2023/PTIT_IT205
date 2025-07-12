package com.data.session04.controller;

import com.data.session04.model.entity.FoodItem;
import com.data.session04.services.impl.CategoryService;
import com.data.session04.services.impl.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/foods")
public class FoodItemController {
    @Autowired
    private FoodItemService foodItemService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listFoods(Model model,
                            @RequestParam(defaultValue = "") String name,
                            @RequestParam(required = false) Long categoryId,
                            @RequestParam(defaultValue = "0") int page) {
        Page<FoodItem> pageData = foodItemService.search(name, categoryId, PageRequest.of(page, 10));
        model.addAttribute("foods", pageData);
        model.addAttribute("name", name);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryService.findAll());
        return "foods";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("foodItem", new FoodItem());
        model.addAttribute("categories", categoryService.findAll());
        return "form-food";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute FoodItem foodItem) {
        foodItemService.save(foodItem);
        return "redirect:/foods";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("foodItem", foodItemService.getById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "form-food";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        foodItemService.delete(id);
        return "redirect:/foods";
    }
}
