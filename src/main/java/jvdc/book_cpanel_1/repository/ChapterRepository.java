package jvdc.book_cpanel_1.repository;

import jvdc.book_cpanel_1.models.Chapter;
import jvdc.book_cpanel_1.models.MangaComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    @Query("SELECT c FROM Chapter c WHERE c.mangaid = :mangaid")
    List<Chapter> queryChaper(@Param("mangaid") int mangaid);

}
