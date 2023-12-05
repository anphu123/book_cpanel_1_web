package jvdc.book_cpanel_1.repository;


import jvdc.book_cpanel_1.models.Chapter;
import jvdc.book_cpanel_1.models.Coin;
import jvdc.book_cpanel_1.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoinReponsitory extends JpaRepository<Coin, Integer> {

    @Transactional
    @Modifying
    @Query("Update Coin c Set c.value = ?1 where c.id = ?2")
    void updateCoinById(int value,int id);



}
