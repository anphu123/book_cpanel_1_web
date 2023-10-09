package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.auth.MyUserDetails;
import jvdc.book_cpanel_1.models.Category;
import jvdc.book_cpanel_1.models.Employee;
import jvdc.book_cpanel_1.models.ImageData;
import jvdc.book_cpanel_1.models.Product;
import jvdc.book_cpanel_1.repository.CategoryRepository;
import jvdc.book_cpanel_1.services.ProductService;
import jvdc.book_cpanel_1.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(path = "/sach")
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private StorageService service;
    @GetMapping("")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("manhinh12.1_DanhSachSach");
        List<ImageData> imageList = service.viewAll();
        List<Product> productList = productService.listAll();
        mv.addObject("productList",productList);
        mv.addObject("imageList", imageList);
        return mv;
    }
    @GetMapping("/them-moi")
    public String addImage(Model model){
        Product product = new Product();
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("product",product);
        return "manhinh12.2_ThemMoi";
    }
    @PostMapping(value = "/them-moi/saveproduct")
    public String addImagePost(
            @RequestParam("image")MultipartFile file,
            @Validated @ModelAttribute("product") Product product, BindingResult result
    ) throws IOException {
        System.out.println(product);
        System.out.println("-----------"+product.getCategories());
        String nameImage = service.uploadImage(file);
        product.setEmployeeName(getAutthenticationId());
        product.setProductImageName(nameImage);
        if(!nameImage.isEmpty()){
            productService.save(product);
        }
        if(result.hasErrors()){
            result.getFieldErrors().forEach(errs ->{
                System.out.println("ten field--:" +errs.getField());
            });
        }
        return "redirect:/sach";
    }
    private  String getAutthenticationId(){
        String nameEmployee  =null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof MyUserDetails) {
            nameEmployee = ((MyUserDetails)principal).getUsername();
        }
        return nameEmployee;
    }

}
