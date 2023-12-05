package jvdc.book_cpanel_1.services;

import jvdc.book_cpanel_1.models.Chapter;
import jvdc.book_cpanel_1.models.Coin;

import jvdc.book_cpanel_1.models.Customer;
import jvdc.book_cpanel_1.repository.CoinReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoinService {

    @Autowired
    CoinReponsitory coinReponsitory;

    public Coin save(Coin coin) {
        return coinReponsitory.save(coin);
    }

    public void modifyCoin(int value,int id) {
        Optional<Coin> coinByid = coinReponsitory.findById(id);
        int newvalue = coinByid.get().getValue() +value;
        coinByid.get().setValue(newvalue);
        coinReponsitory.save(coinByid.get());
    }

    public int subtractPrice(int id, int amountToSubtract) {
        Coin coinByid = coinReponsitory.findById(id).get();

        if (coinByid != null) {
            int currentPrice = coinByid.getValue();
            int newPrice = currentPrice - amountToSubtract;
            if (newPrice >= 0) {
                coinByid.setValue(newPrice);
                coinReponsitory.save(coinByid);
                return newPrice;
            } else {
                // Optional: Handle if the new price becomes negative
                throw new IllegalArgumentException("Amount to subtract exceeds the current price");
            }
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

}
