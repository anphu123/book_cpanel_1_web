package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.models.Category;
import jvdc.book_cpanel_1.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);
        return "categories";
    }

    @PostMapping("/categories/addNew")
    public String saveCategory(@RequestParam("categoriesName") String name){
        Category category = new Category(name);
        categoryRepository.save(category);
        return "redirect:/categories";
    }
}
