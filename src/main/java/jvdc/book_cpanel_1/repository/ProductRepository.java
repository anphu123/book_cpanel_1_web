package jvdc.book_cpanel_1.repository;

import jvdc.book_cpanel_1.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getByproductName(String productName);
    void deleteById(int id);


    @Query("SELECT p FROM Product p  JOIN p.categories c WHERE c.categoryName LIKE %?1%")
    List<Product> getByproductType(String categoryName);
}
