package jvdc.book_cpanel_1.repository;

import jvdc.book_cpanel_1.models.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {
    Optional<ImageData>  findByName(String fileName);
}
