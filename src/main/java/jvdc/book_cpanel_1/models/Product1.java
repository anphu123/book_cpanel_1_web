package jvdc.book_cpanel_1.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String productName;
    private String employeeName;
    private String productAuthor;

    @Column(columnDefinition = "TEXT")
    private String productShortDescription;

    private String productImageName;
    private int soTap;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date(); // initialize updated date



    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();


    public Product1(String productName, String productAuthor, String productShortDescription, String productImageName, int soTap , Set<Category> categories) {
        this.productName = productName;
        this.productAuthor = productAuthor;
        this.productShortDescription = productShortDescription;
        this.productImageName = productImageName;
        this.soTap = soTap;
        this.categories = categories;
    }

    public Product1(String productName, String productAuthor, String productShortDescription, int soTap) {
        this.productName = productName;
        this.productAuthor = productAuthor;
        this.productShortDescription = productShortDescription;
        this.soTap = soTap;

    }


}
