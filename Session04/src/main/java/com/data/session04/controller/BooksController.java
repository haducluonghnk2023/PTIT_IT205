package com.data.session04.controller;

import com.data.session04.model.entity.Books;
import com.data.session04.services.BooksService;
import com.data.session04.services.CategoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BooksController {
    @Autowired
    private BooksService booksService;

    @Autowired
    private CategoryBookService categoryService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "book/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("book", new Books());
        model.addAttribute("categories", categoryService.findAll());
        return "book/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Books book) {
        booksService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "book/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        booksService.deleteById(id);
        return "redirect:/book";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "book/detail";
    }
}

