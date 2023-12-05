package jvdc.book_cpanel_1.repository;

import jvdc.book_cpanel_1.models.Customer;
import jvdc.book_cpanel_1.models.Employee;
import jvdc.book_cpanel_1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT s FROM Customer s WHERE s.customereMail = ?1")
    Optional<Customer>  findCustomerByMail(String customereMail);

    @Query("SELECT s FROM Customer s WHERE s.id = ?1")
    Optional<Customer>  findCustomerByID(Integer id);
    @Query("SELECT s FROM Customer s WHERE s.customereMail = :customereMail")
    public Customer loginByEmail(@Param("customereMail") String customereMail);

    @Transactional
    @Modifying
    @Query("Update Customer c  set c.customerName = ?1, c.customerPassword = ?2, c.customerbirthDate = ?3,c.avatar_url = ?4 where c.id = ?5")
    void updateCustomerByID(String customerName,String customerPassword,String customerbirthDate,String avatar_url,int id);


}
