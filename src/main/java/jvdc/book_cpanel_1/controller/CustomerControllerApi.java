package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.auth.MyUserDetails;
import jvdc.book_cpanel_1.models.Customer;
import jvdc.book_cpanel_1.models.Employee;
import jvdc.book_cpanel_1.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerControllerApi {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/dang-ky")
    public Customer registerNewEmp(@RequestBody Customer customer){
        System.out.println(customer);
        customerService.saveCustomer1(customer);
        return customer;

    }
//
//    @PostMapping("/login")
//    public String authenticateAndGetToken(@RequestBody Customer customer) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getCustomereMail(), customer.getCustomerPassword()));
//        String ketqua =null;
//        if (authentication.isAuthenticated()) {
//            ketqua = "dang nhap thanh cong";
//            System.out.println("dang nhap thanh cong");
//        } else {
//            throw new UsernameNotFoundException("invalid user request !");
//        }
//        System.out.println("111111111");
//        return ketqua;
//    }

    @PostMapping(value = "/dang-nhap")
    public Customer login(@RequestBody Customer customer){
        System.out.println("Controller layer:Customer Login "+customer.getCustomereMail());
        Customer customer1 = customerService.loadCustomerByEmail(customer.getCustomereMail());
        if(customer1 !=null && customer.getCustomerPassword().equals(customer1.getCustomerPassword())){
            System.out.println("Controller layer: Dang Nhap Thanh Cong Tu Phia SERVER");
            return customer1;
        }else
            throw new UsernameNotFoundException("invalid user request !");
    }

}
