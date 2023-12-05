package jvdc.book_cpanel_1.services;


import jvdc.book_cpanel_1.models.Manga;
import jvdc.book_cpanel_1.models.Product;
import jvdc.book_cpanel_1.repository.MangaReponsitory;
import jvdc.book_cpanel_1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {


    @Autowired
    MangaReponsitory mangaReponsitory;

    @Autowired
    ProductRepository productRepository;
    public List<Manga> listAll() {
        return mangaReponsitory.findAll();
    }
    public Product save(Product product) {
        return productRepository.save(product);
    }


}
