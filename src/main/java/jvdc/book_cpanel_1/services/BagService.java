package jvdc.book_cpanel_1.services;


import jvdc.book_cpanel_1.models.Bag;
import jvdc.book_cpanel_1.models.Chapter;
import jvdc.book_cpanel_1.repository.BagReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BagService {
    @Autowired
    BagReponsitory bagReponsitory;

    public void saveBag(Bag bag){
        bag.setType("paytoview");
        bagReponsitory.save(bag);
    }

    public Bag finditem(Integer idcustomer,Integer idchapter){
        List<Bag> bagList = bagReponsitory.getBagItem(idcustomer,idchapter);
        if (!bagList.isEmpty()){
            Bag bag = bagReponsitory.getBagItem(idcustomer,idchapter).get(0);
            return bag;
        }
        return null;
    }
}
