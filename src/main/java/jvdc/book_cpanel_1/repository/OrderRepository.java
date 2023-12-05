package jvdc.book_cpanel_1.repository;

import jvdc.book_cpanel_1.models.Order;
import jvdc.book_cpanel_1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Transactional
    @Modifying
    @Query("Update Order o  set o.da_thanh_toan = true, o.momo = ?1 where o.id = ?2")
    void updateOrderById(String momo,int id);
}

