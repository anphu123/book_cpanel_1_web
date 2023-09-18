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

    private String productType;

    private String productAuthor;

    @Column(columnDefinition = "TEXT")
    private String productDescription;

    @Column(columnDefinition = "TEXT")
    private String productShortDescription;

    private double productPrice;
    private String productImageName;
    private int soTap;
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


    public Product(String productName,double productPrice, String productAuthor, String productDescription, String productShortDescription,String productImageName,int soTap, int favoriteNumber,Set<Category> categories) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAuthor = productAuthor;
        this.productDescription = productDescription;
        this.productShortDescription = productShortDescription;
        this.productImageName = productImageName;
        this.soTap = soTap;
        this.favoriteNumber = favoriteNumber;
        this.categories = categories;
    }
    public Product(String productName,double productPrice, String productAuthor, String productDescription,String productShortDescription, String productImageName,int soTap, int favoriteNumber) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAuthor = productAuthor;
        this.productDescription = productDescription;
        this.productShortDescription = productShortDescription;
        this.productImageName = productImageName;
        this.soTap = soTap;
        this.favoriteNumber = favoriteNumber;

    }



    public Product(String productName, String productType, String productAuthor, String productShortDescription, double productPrice, String productImageName, int soTap) {
        this.productName = productName;
        this.productType = productType;
        this.productAuthor = productAuthor;
        this.productShortDescription = productShortDescription;
        this.productPrice = productPrice;
        this.productImageName = productImageName;
        this.soTap = soTap;
    }

    public void addCategory(Category category){this.categories.add(category);}
    public void removeCategory(Category category){
        this.categories.remove(category);
    }
}