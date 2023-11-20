package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.models.*;
import jvdc.book_cpanel_1.repository.BagReponsitory;
import jvdc.book_cpanel_1.repository.LinkRepository;
import jvdc.book_cpanel_1.repository.MangaCommentRepository;
import jvdc.book_cpanel_1.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    MangaCommentService mangaCommentService;

    @Autowired
    MangaCommentRepository mangaCommentRepository;
    @Autowired
    private ChapterService chapterService;

//    @Autowired
//    private BagService bagService;

    @Autowired
    private BagReponsitory bagReponsitory;

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
    @GetMapping(value = "/category/{categoryName}")
    public List<Product>  getProductFollowByType(@PathVariable(name = "categoryName") String categoryName){
        List<Product>  products = productService.listAllQueryByType(categoryName);
        return products;
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
    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable(name = "id") int id){
        Product product = productService.getProductById(id);
        return product;
    }



    @PostMapping(value = "/{id}/comment/addNewsComment")
    public ResponseEntity<String> addNewComment(
            @PathVariable(name = "id") int id,
            @RequestBody MangaComment mangaComment) throws IOException {
        Product product = productService.getProductById(id);
        product.addComment(mangaComment);
        System.out.println(mangaComment);
        productService.save(product);
        return  new ResponseEntity<>("Them moi Thanh Cong", HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}/comments/get")
    public List<MangaComment> getMangaComment(@PathVariable(name = "id")Integer id,
                                              @RequestParam("page") int page
                                        ){
        Page<MangaComment> mangaCommentList = mangaCommentService.mangaCommentList(
                id,
                (Pageable) PageRequest.of(page,6));
        List<MangaComment> mangaCommentList2= mangaCommentList.toList();
        return mangaCommentList2;
    }


}
