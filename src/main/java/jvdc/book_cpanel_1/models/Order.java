package jvdc.book_cpanel_1.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "order_")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean da_thanh_toan;
    private String noidung;
    private String momo;
    private int customer_id;


    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;
    public Order(boolean da_thanh_toan, String noidung, int customer_id) {
        this.da_thanh_toan = da_thanh_toan;
        this.noidung = noidung;
        this.customer_id = customer_id;
    }




}
