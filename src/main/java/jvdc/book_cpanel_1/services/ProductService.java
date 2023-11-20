package jvdc.book_cpanel_1.services;

import jvdc.book_cpanel_1.models.*;
import jvdc.book_cpanel_1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> listAll() {
        return productRepository.findAll();
    }
    public List<Product> listAllQueryByType(String categoryName) {
        return productRepository.getByproductType(categoryName);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else
            try {
                throw new Exception("Username not found " + id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }



}
