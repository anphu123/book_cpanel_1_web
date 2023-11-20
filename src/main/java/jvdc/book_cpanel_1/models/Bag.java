package jvdc.book_cpanel_1.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Getter
@Setter
@Table(name = "bag")
public class Bag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idbag;
    private Integer idcustomer;
    private Integer idchapter;

    private String type;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    public Bag(Integer idcustomer, Integer idchapter) {
        this.idcustomer = idcustomer;
        this.idchapter = idchapter;
    }
}
