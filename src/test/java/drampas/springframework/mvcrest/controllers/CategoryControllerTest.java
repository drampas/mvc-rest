package drampas.springframework.mvcrest.controllers;

import drampas.springframework.mvcrest.api.model.CategoryDTO;
import drampas.springframework.mvcrest.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryControllerTest {
    @Mock
    CategoryService categoryService;
    @InjectMocks
    CategoryController categoryController;
    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc=MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void getAllCategories() throws Exception {
        CategoryDTO category1=new CategoryDTO(1L,"fresh");
        CategoryDTO category2=new CategoryDTO(2L,"dried");
        List<CategoryDTO> categories=Arrays.asList(category1,category2);

        Mockito.when(categoryService.getCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories",hasSize(2)));
    }

    @Test
    void getCategory() throws Exception {
        CategoryDTO categoryDTO=new CategoryDTO(1L,"fruit");

        Mockito.when(categoryService.findByName(ArgumentMatchers.anyString())).thenReturn(categoryDTO);

        mockMvc.perform(get("/api/categories/fruit").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo("fruit")));
    }
}