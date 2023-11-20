package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.models.Coin;
import jvdc.book_cpanel_1.models.Customer;
import jvdc.book_cpanel_1.models.Order;
import jvdc.book_cpanel_1.services.CoinService;
import jvdc.book_cpanel_1.services.CustomerService;
import jvdc.book_cpanel_1.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v2/coins")
public class CoinControllerApi {

    @Autowired
    private CoinService coinService;
    //Thong Tin Ca Nhan

    @PostMapping(value = "/order/{coin_id}/{value}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Coin> add10coins(@PathVariable(name = "value") int value, @PathVariable(name = "coin_id") int coin_id){
        coinService.modifyCoin(value,coin_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
