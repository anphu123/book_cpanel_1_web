package jvdc.book_cpanel_1.repository;

import jvdc.book_cpanel_1.models.MangaComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaCommentRepository extends JpaRepository<MangaComment, Integer> {
    @Query("SELECT m FROM MangaComment m WHERE m.mangaid = :mangaid")
    Page<MangaComment> queryMangaComment(@Param("mangaid") Integer mangaid, Pageable pageable);



}
