package jvdc.book_cpanel_1.services;

import jvdc.book_cpanel_1.models.Category;
import jvdc.book_cpanel_1.models.Chapter;
import jvdc.book_cpanel_1.repository.CategoryRepository;
import jvdc.book_cpanel_1.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


}
