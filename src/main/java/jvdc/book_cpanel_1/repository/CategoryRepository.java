package jvdc.book_cpanel_1.repository;

import jvdc.book_cpanel_1.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> getByCategoryName(String categoryName);

    @Query("from Category where categoryName=:categoryName")
    String findByCategoryName(String categoryName);
}
