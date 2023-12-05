package jvdc.book_cpanel_1.models;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

    @NotNull
    private String productName;
    private String employeeName;
    private String productAuthor;

    @Column(columnDefinition = "TEXT")
    private String productShortDescription;
    private int price;
    private int view;

    private String productImageName;
    private int soTap;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date(); // initialize updated date

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mangaid", referencedColumnName = "id")
    private List<Chapter> chapter;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mangaid", referencedColumnName = "id")
    private List<MangaComment> mangaCommentList;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();


    public Product(String productName,  String productAuthor,  String productShortDescription,String productImageName,int soTap ,Set<Category> categories) {
        this.productName = productName;
        this.productAuthor = productAuthor;
        this.productShortDescription = productShortDescription;
        this.productImageName = productImageName;
        this.soTap = soTap;
        this.categories = categories;
    }


    public Product(String productName, String productAuthor, String productShortDescription, int soTap) {
        this.productName = productName;
        this.productAuthor = productAuthor;
        this.productShortDescription = productShortDescription;
        this.soTap = soTap;

    }


    public Product(Integer id, String productName, String productImageName) {
        this.id = id;
        this.productName = productName;
        this.productImageName = productImageName;
    }

    public void addCategory(Category category){this.categories.add(category);}
    public void removeCategory(Category category){
        this.categories.remove(category);
    }

    public Product(Integer id, String productName, String employeeName, String productAuthor, String productShortDescription, String productImageName, int soTap, Date updatedAt, Set<Category> categories) {
        this.id = id;
        this.productName = productName;
        this.employeeName = employeeName;
        this.productAuthor = productAuthor;
        this.productShortDescription = productShortDescription;
        this.productImageName = productImageName;
        this.soTap = soTap;
        this.updatedAt = updatedAt;
        this.categories = categories;
    }

    public Product(Integer id, List<MangaComment> mangaCommentList) {
        this.id = id;
        this.mangaCommentList = mangaCommentList;
    }

    public Product(List<MangaComment> mangaCommentList) {
        this.mangaCommentList = mangaCommentList;
    }

    public void addComment(MangaComment mangaComment){this.mangaCommentList.add(mangaComment);}
    public void removeComment(MangaComment mangaComment){
        this.mangaCommentList.remove(mangaComment);
    }

}