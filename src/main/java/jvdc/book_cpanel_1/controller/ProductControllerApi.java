package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.models.*;
import jvdc.book_cpanel_1.repository.LinkRepository;
import jvdc.book_cpanel_1.services.*;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private MangaService mangaService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private LinkService linkService;

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

    @GetMapping(value = "/comic")
    public List<Product> getManga(){
        List<Product> productList = productService.listAll();
        return productList;
    }

    @GetMapping(value = "/chapter/{mangaid}")
    public List<Chapter> getChapter(@PathVariable(name = "mangaid")Integer mangaid){
        List<Chapter> chapterList = chapterService.getallChapter(mangaid);
        return chapterList;
    }

    @GetMapping(value = "/link/{linkid}")
    public List<Link> getLink(@PathVariable(name = "linkid")Integer linkid){
        List<Link> linkList= linkService.getallLink(linkid);
        return linkList;
    }

}
