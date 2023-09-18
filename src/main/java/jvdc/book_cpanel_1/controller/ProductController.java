package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.models.Employee;
import jvdc.book_cpanel_1.models.ImageData;
import jvdc.book_cpanel_1.models.Product;
import jvdc.book_cpanel_1.services.ProductService;
import jvdc.book_cpanel_1.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@RequestMapping(path = "/sach")
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private StorageService service;
    @GetMapping("")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("manhinh12.1_DanhSachSach");
        List<ImageData> imageList = service.viewAll();
        mv.addObject("imageList", imageList);
        return mv;
    }
    @GetMapping("/them-moi")
    public ModelAndView addImage(){
        return new ModelAndView("manhinh12.2_ThemMoi");
    }

    @PostMapping("/them-moi")
    public String addImagePost(
            @RequestParam("image")MultipartFile file,
            @RequestParam("name") String productName,
            @RequestParam("the-loai") String productType,
            @RequestParam("author") String productAuthor,
            @RequestParam("so-tap") int soTap,
            @RequestParam("gia") double productPrice,
            @RequestParam("mo-ta-ngan") String productShortDescription
            ) throws IOException{
        String nameImage = service.uploadImage(file);
        Product product = new Product(productName,productType,productAuthor,productShortDescription,productPrice,nameImage,soTap);
        if(!nameImage.isEmpty()){
            productService.save(product);
        }
        return "redirect:/sach";
    }


}
