package jvdc.book_cpanel_1.services;


import jvdc.book_cpanel_1.models.Coin;
import jvdc.book_cpanel_1.models.Customer;
import jvdc.book_cpanel_1.models.Order;
import jvdc.book_cpanel_1.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void SaveOrder(Order order) {
        orderRepository.save(order);
    }


}
