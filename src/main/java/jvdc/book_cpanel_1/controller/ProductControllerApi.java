package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.models.Product;
import jvdc.book_cpanel_1.services.ProductService;
import jvdc.book_cpanel_1.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductControllerApi {

    @Autowired
    private StorageService service;

    @PostMapping(value = "/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException{
        String uploadImage = service.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping(value = "/image/{filename}")
    public ResponseEntity<?> downloadImage(@PathVariable(name = "filename") String filename){
        byte[] imageData = service.downloadImage(filename);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

//    @Autowired
//    private ProductService productService;
//    //    http://localhost:8080/api/v1/product
//    @GetMapping
//    public List<Product> getProduct(){
//        List<Product> productList = productService.listAll();
//        return productList;
//    }

//    @GetMapping("/greet")
//    public GreetResponse greet(){
//        return new GreetResponse ("Hello");
//    }
//
//    record GreetResponse(String greet){};

}
