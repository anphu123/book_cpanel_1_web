package jvdc.book_cpanel_1.services;


import jvdc.book_cpanel_1.models.Bag;
import jvdc.book_cpanel_1.models.Chapter;
import jvdc.book_cpanel_1.models.Product;
import jvdc.book_cpanel_1.repository.BagReponsitory;
import jvdc.book_cpanel_1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BagService {
    @Autowired
    BagReponsitory bagReponsitory;

    @Autowired
    ProductRepository productRepository;

    public void saveIemPayToViewInBag(Bag bag){
        bag.setType("paytoview");
        bagReponsitory.save(bag);
    }
    // Method to find a bag item by customer ID and product ID
    public Bag findBagItem(Integer customerId, Integer idfavorite) {
        List<Bag> bagList = bagReponsitory.findByCustomerIdAndProductId(customerId,idfavorite);
        if (!bagList.isEmpty()){
            Bag bag = bagList.get(0);
            return bag;
        }
        return null;
    }

    // Method to remove an item from the bag
    public void removeItemFromBag(Bag bagItem) {
        bagReponsitory.delete(bagItem);
    }
    public void saveIemfalvoritetoBag(Bag bag){
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
    public List<Product> finditemflavorite(Integer idcustomer){
        List<Bag> bagList = bagReponsitory.getBagItemFavorite(idcustomer);
        List<Product>  favoritelist= new ArrayList<>();
        if (!bagList.isEmpty()){
            for (Bag bag : bagList) {
                Product tempProduct = productRepository.findById(bag.getIdfavorite()).get();
                favoritelist.add(tempProduct);
            }
            return favoritelist;
        }
        return null;
    }
}
