package jvdc.book_cpanel_1.services;

import jvdc.book_cpanel_1.models.Chapter;
import jvdc.book_cpanel_1.models.Link;
import jvdc.book_cpanel_1.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {

    @Autowired
    LinkRepository linkRepository;
    public List<Link> getallLink(Integer id){
        List<Link> linkList= linkRepository.queryLink(id);
        return linkList;
    }
}
