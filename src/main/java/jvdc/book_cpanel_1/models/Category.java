package jvdc.book_cpanel_1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "categorys")
public class Category {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "category_name")
    @NotNull
    private String categoryName;


    public Category(String categoryName) {
        this.categoryName = categoryName;
    }


}