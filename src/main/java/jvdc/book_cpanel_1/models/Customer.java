package jvdc.book_cpanel_1.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "customerPassword")
    private String customerPassword;

    @Column(name = "customereMail")
    private String customereMail;

    @Column(name = "customerbirthDate")
    private Date customerbirthDate;

    @Column(name = "enabled_CS")
    private boolean enabled_CS = true;

    public String getCustomereMail() {
        return customereMail;
    }

    public void setCustomereMail(String customereMail) {
        this.customereMail = customereMail;
    }


//    @OneToMany
//    private List<SellerComment> sellerComment;
//
//    @OneToMany
//    private List<ProductComment> productComment;
//
//    @ManyToOne
//    private CreditCard creditCard;
//
//    @OneToMany
//    private List<Address> address;
//
//    @OneToMany
//    private List<PromoCode> promoCode;
}
