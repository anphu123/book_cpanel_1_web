package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.auth.MyUserDetails;
import jvdc.book_cpanel_1.models.*;
import jvdc.book_cpanel_1.repository.BagReponsitory;
import jvdc.book_cpanel_1.repository.OrderRepository;
import jvdc.book_cpanel_1.services.BagService;
import jvdc.book_cpanel_1.services.CoinService;
import jvdc.book_cpanel_1.services.CustomerService;
import jvdc.book_cpanel_1.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerControllerApi {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CoinService coinService;

    @Autowired
    private BagService bagService;

    //Thong Tin Ca Nhan
    @PostMapping(value = "/dang-ky")
    public Customer registerNewEmp(@RequestBody Customer customer){
        System.out.println(customer);
        customerService.saveCustomer1(customer);
        return customer;

    }

    @PostMapping(value = "/dang-nhap")
    public ResponseEntity<Customer> login(@RequestBody Customer customer){
        Customer customer1 = customerService.loadCustomerByEmail(customer.getCustomereMail());
        if(customer1.getCustomerName()==null){
            return new ResponseEntity<>(customer, HttpStatus.NOT_FOUND);
        }
        if(!customer1.getCustomerPassword().equals(customer.getCustomerPassword())){
            return new ResponseEntity<>(customer, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer1, HttpStatus.OK);
    }

    @PostMapping(value = "/cap-nhap")
    public ResponseEntity<Customer> capnhap(@RequestBody Customer customer){
        customerService.updateCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }


    @GetMapping(value = "/{id}")
    public Customer getCustomerbyId(@PathVariable(name = "id") int id){
        Customer customer = customerService.getCustomerById(id);
        return customer;
    }
    //Quan ly Order
    @PostMapping(value = "/order/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        orderService.SaveOrder(order);
        return  new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @PostMapping(value = "/order/update/{id}/{momo}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Order> updateOrder(@PathVariable(name = "id") int id,@PathVariable(name = "momo") String momo){
        orderRepository.updateOrderById(momo,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //Quan ly item Trong bag

    @PostMapping(value = "/bag/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Bag> createOrder(@RequestBody Bag bag){
        bagService.saveBag(bag);
        return  new ResponseEntity<>(bag, HttpStatus.CREATED);
    }
    @GetMapping(value = "bag/checkpaytoview/{customerid}/{chapterid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> getItemFromBag(@PathVariable Integer customerid,@PathVariable Integer chapterid){
        Bag bag= bagService.finditem(customerid,chapterid);
        if (bag!=null){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.OK);
    }

}
