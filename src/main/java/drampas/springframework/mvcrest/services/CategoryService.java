package drampas.springframework.mvcrest.services;

import drampas.springframework.mvcrest.api.model.CategoryDTO;


import java.util.List;

public interface CategoryService {
    public List<CategoryDTO> getCategories();
    public CategoryDTO findByName(String name);
}
