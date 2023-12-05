package jvdc.book_cpanel_1.repository;

import jvdc.book_cpanel_1.models.Bag;
import jvdc.book_cpanel_1.models.Customer;
import jvdc.book_cpanel_1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BagReponsitory extends JpaRepository<Bag, Integer> {

    @Query("SELECT b FROM Bag b WHERE b.idcustomer = :idcustomer and b.idchapter = :idchapter and b.type = 'paytoview'")
   List<Bag> getBagItem(@Param("idcustomer") int idcustomer,@Param("idchapter") int idchapter);

    @Query("SELECT b FROM Bag b WHERE b.idcustomer = :idcustomer  and b.type = 'addtoflavor'")
    List<Bag> getBagItemFavorite(@Param("idcustomer") int idcustomer);

    @Query("SELECT b FROM Bag b WHERE b.idcustomer = :idcustomer and b.idfavorite = :idfavorite and b.type = 'addtoflavor'")
    List<Bag> findByCustomerIdAndProductId(@Param("idcustomer") int idcustomer,@Param("idfavorite") int idfavorite);

}
