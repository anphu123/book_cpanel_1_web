package jvdc.book_cpanel_1.repository;

import jvdc.book_cpanel_1.models.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MangaRepository extends JpaRepository<Manga, Integer> {
}
