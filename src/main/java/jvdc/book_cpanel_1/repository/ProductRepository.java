package jvdc.book_cpanel_1.repository;

import jvdc.book_cpanel_1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getByproductName(String productName);
    void deleteById(int id);
}
