package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.auth.MyUserDetails;
import jvdc.book_cpanel_1.models.*;
import jvdc.book_cpanel_1.repository.BagReponsitory;
import jvdc.book_cpanel_1.repository.CustomerRepository;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerControllerApi {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
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

    @PostMapping(value = "/cap-nhap-profile/{customerid}/{customerName}/{customerPassword}/{customerbirthDate}/{avatar_url}/{addressid}/{street}/{city}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> capnhap(
            @PathVariable Integer customerid,
            @PathVariable String customerName,
            @PathVariable String customerPassword,
            @PathVariable String customerbirthDate,
            @PathVariable String avatar_url,
            @PathVariable Integer addressid,
            @PathVariable String street,
            @PathVariable String city
            ){
        try {
            System.out.println("Avatar URL: " + avatar_url);
            Address newaddress = new Address(addressid,"Viet Nam",city,street);
            customerService.updateCustomer(customerid, customerName, customerPassword, customerbirthDate,avatar_url, newaddress);
            return new ResponseEntity<>("Image URL updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update image URL", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(value = "/cap-nhap")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> capnhap(
            @PathVariable Integer customerid
            ,@PathVariable Integer chapterid){
        Bag bag= bagService.finditem(customerid,chapterid);
        if (bag!=null){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.OK);
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


    @GetMapping(value = "bag/checkflavorItem/{customerid}/{idflavorite}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> checkflavorItem(  @PathVariable("customerid") Integer customerid,
                                                     @PathVariable("idflavorite") Integer idflavorite){
        Bag bag= bagService.findBagItem(customerid,idflavorite);
        if (bag!=null){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.OK);
    }
    @PostMapping(value = "/bag/addtobagFavoriteItem/{customerid}/{idflavorite}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> saveIemFlavorinBag(
            @PathVariable("customerid") Integer customerid
            ,@PathVariable("idflavorite") Integer idflavorite){
        Bag bagitemPaytoview = new Bag();
        bagitemPaytoview.setIdcustomer(customerid);
        bagitemPaytoview.setIdfavorite(idflavorite);
        bagitemPaytoview.setType("addtoflavor");
        bagService.saveIemfalvoritetoBag(bagitemPaytoview);
        if (bagitemPaytoview!=null){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.OK);
    }
    @DeleteMapping(value = "/bag/removeFromBagFavoriteItem/{customerid}/{idflavorite}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> removeFlavoriteItemFromBag(
            @PathVariable("customerid") Integer customerid
            ,@PathVariable("idflavorite") Integer idflavorite){
        Bag bagItemToRemove = bagService.findBagItem(customerid, idflavorite); // Assuming you have a method to find the bag item
        if (bagItemToRemove != null) {
            bagService.removeItemFromBag(bagItemToRemove); // Assuming you have a method to remove the bag item
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
    @PostMapping(value = "/bag/addtobagpaytoviewItem/{customerid}/{chapterid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> saveIemPayToViewInBag(
            @PathVariable("customerid") Integer customerid
            ,@PathVariable("chapterid") Integer chapterid){
        Bag bagitemPaytoview = new Bag(customerid,chapterid);
        bagService.saveIemPayToViewInBag(bagitemPaytoview);
        if (bagitemPaytoview!=null){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.OK);
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
    @GetMapping(value = "bag/alreadyflavor/{customerid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Product>> getItemFavorFromBag(@PathVariable Integer customerid){
        List<Product> listflavorProduct= bagService.finditemflavorite(customerid);
        if (listflavorProduct!=null){
            return new ResponseEntity<>(listflavorProduct,HttpStatus.OK);
        }
        return new ResponseEntity<>(listflavorProduct,HttpStatus.OK);
    }


}
