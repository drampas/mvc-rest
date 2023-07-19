package drampas.springframework.mvcrest.controllers;

import drampas.springframework.mvcrest.api.model.CategoryDTO;
import drampas.springframework.mvcrest.api.model.CategoryListDTO;
import drampas.springframework.mvcrest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories(){
        return new ResponseEntity<CategoryListDTO>(
                new CategoryListDTO(categoryService.getCategories()), HttpStatus.OK);
    }
    @GetMapping("/{name}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable String name){
        CategoryDTO categoryDTO=categoryService.findByName(name);
        return new ResponseEntity<CategoryDTO>(categoryDTO,HttpStatus.OK);
    }
}
