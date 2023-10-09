package jvdc.book_cpanel_1.services;


import jvdc.book_cpanel_1.models.Employee;
import jvdc.book_cpanel_1.models.Manga;
import jvdc.book_cpanel_1.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {

    @Autowired
    MangaRepository mangaRepository;

    public List<Manga> listAll() {
        return mangaRepository.findAll();
    }

}
