package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.models.Product;
import jvdc.book_cpanel_1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductControllerApi {

    @Autowired
    private ProductService productService;
    //    http://localhost:8080/api/v1/product
    @GetMapping
    public List<Product> getProduct(){
        List<Product> productList = productService.listAll();
        return productList;
    }

//    @GetMapping("/greet")
//    public GreetResponse greet(){
//        return new GreetResponse ("Hello");
//    }
//
//    record GreetResponse(String greet){};

}
