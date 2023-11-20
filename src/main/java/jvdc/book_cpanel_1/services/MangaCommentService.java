package jvdc.book_cpanel_1.services;

import jvdc.book_cpanel_1.models.MangaComment;
import jvdc.book_cpanel_1.repository.MangaCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class MangaCommentService {

    @Autowired
    MangaCommentRepository mangaCommentRepository;
    public Page<MangaComment> mangaCommentList(Integer id, Pageable pageable){
        Page<MangaComment> mangaCommentList= mangaCommentRepository.queryMangaComment(id,pageable);
        return mangaCommentList;
    };


}
