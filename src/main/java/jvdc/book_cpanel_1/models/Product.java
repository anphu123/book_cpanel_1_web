package jvdc.book_cpanel_1.models;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@ToString
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String productName;

    private String productBrand;

    @Column(columnDefinition = "TEXT")
    private String productDescription;

    @Column(columnDefinition = "TEXT")
    private String productShortDescription;

//    @Min(value = 1, message = "{Min.price.message}")
    private double productPrice;


    private String productImageUrl;

    //    @Min(value = 1, message = "{Min.stock.message}")
    private int stock;

    //    @Min(value = 0)
    private int favoriteNumber = 0;


//    @ManyToOne
//    private Employee employee;
//
//    @OneToMany
//    private List<ProductComment> productComment;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();


    public Product(String productName,double productPrice, String productBrand, String productDescription, String productShortDescription,String productImageUrl,int stock, int favoriteNumber,Set<Category> categories) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productShortDescription = productShortDescription;
        this.productImageUrl = productImageUrl;
        this.stock = stock;
        this.favoriteNumber = favoriteNumber;
        this.categories = categories;
    }
    public Product(String productName,double productPrice, String productBrand, String productDescription,String productShortDescription, String productImageUrl,int stock, int favoriteNumber) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productShortDescription = productShortDescription;
        this.productImageUrl = productImageUrl;
        this.stock = stock;
        this.favoriteNumber = favoriteNumber;

    }


    public void addCategory(Category category){this.categories.add(category);}
    public void removeCategory(Category category){
        this.categories.remove(category);
    }
}