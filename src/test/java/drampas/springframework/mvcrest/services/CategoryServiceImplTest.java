package drampas.springframework.mvcrest.services;

import drampas.springframework.mvcrest.api.mapper.CategoryMapper;
import drampas.springframework.mvcrest.api.model.CategoryDTO;
import drampas.springframework.mvcrest.domain.Category;
import drampas.springframework.mvcrest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceImplTest {
    CategoryService categoryService;
    @Mock
    CategoryRepository categoryRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService=new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    void getCategories() {
        List<Category> categories= Arrays.asList(new Category(),new Category(),new Category());
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOS=categoryService.getCategories();
        assertEquals(3,categoryDTOS.size());
    }

    @Test
    void findByName() {
        Category category=new Category();
        category.setName("fruit");
        category.setId(1L);
        Mockito.when(categoryRepository.findByName(ArgumentMatchers.anyString())).thenReturn(category);

        CategoryDTO categoryDTO=categoryService.findByName("fruit");
        assertEquals("fruit",categoryDTO.getName());
        assertEquals(1L,categoryDTO.getId());
    }
}