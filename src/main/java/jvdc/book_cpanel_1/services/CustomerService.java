package jvdc.book_cpanel_1.services;
import jvdc.book_cpanel_1.auth.MyUserDetails;
import jvdc.book_cpanel_1.models.Customer;
import jvdc.book_cpanel_1.models.Employee;
import jvdc.book_cpanel_1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public void saveCustomer1(Customer customer) {
        Optional<Customer>  customerbyEmail = customerRepository.findCustomerByMail(customer.getCustomereMail());
        if(customerbyEmail.isPresent()){
            throw new IllegalStateException("Tai Khoan Email da duoc su dung");
        }
        customerRepository.save(customer);
    }

    public Customer loadCustomerByEmail(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.loginByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return customer;
    }

    public Customer get(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else
            try {
                throw new Exception("Username not found " + id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

}
