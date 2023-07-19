package drampas.springframework.mvcrest.api.mapper;

import drampas.springframework.mvcrest.api.model.CategoryDTO;
import drampas.springframework.mvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE= Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
