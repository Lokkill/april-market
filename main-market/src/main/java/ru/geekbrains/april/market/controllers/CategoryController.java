package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.*;
import ru.geekbrains.april.market.models.Category;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
//    private final CategoryService categoryService;
//
//    @GetMapping("/{id}")
//    public Category getOneCategoryById(@PathVariable Long id) {
//        return categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category doesn't exists: " + id));
//    }
}
