package drampas.springframework.mvcrest.repositories;

import drampas.springframework.mvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    public Category findByName(String name);
}
