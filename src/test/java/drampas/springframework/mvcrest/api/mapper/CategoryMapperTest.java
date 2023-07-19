package drampas.springframework.mvcrest.api.mapper;

import drampas.springframework.mvcrest.api.model.CategoryDTO;
import drampas.springframework.mvcrest.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {
    CategoryMapper categoryMapper=CategoryMapper.INSTANCE;
    @Test
    void categoryToCategoryDTO(){
        Category category=new Category();
        category.setName("fruit");
        category.setId(1L);

        CategoryDTO categoryDTO=categoryMapper.categoryToCategoryDTO(category);

        assertEquals("fruit",categoryDTO.getName());
        assertEquals(1L,categoryDTO.getId());
    }
}