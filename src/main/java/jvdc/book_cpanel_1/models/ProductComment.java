package jvdc.book_cpanel_1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "product_comment")
public class ProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "rating")
    private int rating;

    @Column(name = "create_date")
    private Date createDate;

//    @ManyToOne
//    private User user;

    @ManyToOne
    private Product product;

    public ProductComment(String title, String body, int rating, Date createDate) {
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.createDate = createDate;
    }
}