package jvdc.book_cpanel_1.repository;

import jvdc.book_cpanel_1.models.Chapter;
import jvdc.book_cpanel_1.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {
    @Query("SELECT l FROM Link l  WHERE l.linkid = :linkid")
    List<Link> queryLink(@Param("linkid") int linkid);
}
