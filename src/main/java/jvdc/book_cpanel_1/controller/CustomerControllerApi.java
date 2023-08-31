package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.auth.MyUserDetails;
import jvdc.book_cpanel_1.models.Customer;
import jvdc.book_cpanel_1.models.Employee;
import jvdc.book_cpanel_1.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "/dang-nhap")
    public void login(@RequestBody Customer customer){
        customerService.loadCustomerByEmail(customer.getCustomereMail());
        System.out.println(customer);
    }

//    @Override
//    public Customer loadUserByUsername(String email) throws Exception {
//        Customer customer = customerService.getEmployeesByMailEmployee(email);

//        System.out.println(employee.getNameEmployee());
//        if (employee == null) {
//            throw new UsernameNotFoundException("Could not find user");
//        }
//        return new MyUserDetails(employee);
//    }

//    {
//            "customerName": "qc_user1",
//            "customerPassword": "book",
//             "customereMail" : "qc_user1@gmail.com"
//            "customerbirthDate": "01/01/1999",
//            "enabled_CS": true
//    }
}
