package com.data.session04.controller;

import com.data.session04.model.entity.CategoryBook;
import com.data.session04.services.CategoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryBookController {
    @Autowired
    private CategoryBookService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", service.findAll());
        return "category/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("categoryBook", new CategoryBook());
        return "category/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute CategoryBook categoryBook) {
        service.save(categoryBook);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("categoryBook", service.findById(id));
        return "category/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/category";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("category", service.findById(id));
        return "category/detail";
    }
}
