package jvdc.book_cpanel_1.repository;

import jvdc.book_cpanel_1.models.Category;
import jvdc.book_cpanel_1.models.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> getByCategoryName(String categoryName);

    @Query("from Category where categoryName=:categoryName")
    String findByCategoryName(String categoryName);


}
