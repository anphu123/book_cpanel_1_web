package jvdc.book_cpanel_1.services;

import jvdc.book_cpanel_1.models.Chapter;
import jvdc.book_cpanel_1.models.Customer;
import jvdc.book_cpanel_1.models.Employee;
import jvdc.book_cpanel_1.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {
    @Autowired
    ChapterRepository chapterRepository;

    public List<Chapter> getallChapter(Integer id){
        List<Chapter> chapterList= chapterRepository.queryChaper(id);
        return chapterList;
    }


}
