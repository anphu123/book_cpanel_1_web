package jvdc.book_cpanel_1.models;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.List;
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
    @NotNull
    @Column(name = "customerName")
    private String customerName;
    @NotNull
    @Column(name = "customerPassword")
    private String customerPassword;

    @NotNull
    @Column(name = "customereMail")
    private String customereMail;

    @Column(name = "customerbirthDate")
    private String customerbirthDate;

    @Column(name = "avatar_url")
    private String avatar_url;

    @Column(name = "enabled_CS")
    private boolean enabled_CS ;


    public String getCustomereMail() {
        return customereMail;
    }

    public void setCustomereMail(String customereMail) {
        this.customereMail = customereMail;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coin_id", referencedColumnName = "id")
    private Coin coin;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<Order> orderList;

    public Customer(int id, String customerName, String customerPassword, String customereMail, String customerbirthDate, boolean enabled_CS, Address address) {
        this.id = id;
        this.customerName = customerName;
        this.customerPassword = customerPassword;
        this.customereMail = customereMail;
        this.customerbirthDate = customerbirthDate;
        this.enabled_CS = enabled_CS;
        this.address = address;
    }
}
