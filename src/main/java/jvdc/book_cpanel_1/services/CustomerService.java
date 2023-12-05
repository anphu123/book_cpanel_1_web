package jvdc.book_cpanel_1.services;
import jvdc.book_cpanel_1.models.*;
import jvdc.book_cpanel_1.repository.AddressRepository;
import jvdc.book_cpanel_1.repository.CoinReponsitory;
import jvdc.book_cpanel_1.repository.CustomerRepository;
import jvdc.book_cpanel_1.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CoinReponsitory coinReponsitory;

    @Autowired
    AddressRepository addressRepository;


    @Autowired
    OrderRepository orderRepository;
    public void saveCustomer1(Customer customer) {
        Optional<Customer>  customerbyEmail = customerRepository.findCustomerByMail(customer.getCustomereMail());
        if(customerbyEmail.isPresent()){
            throw new IllegalStateException("Tai Khoan Email da duoc su dung");
        }
        customerRepository.save(customer);
        System.out.println(customer.getId());
        Coin coin = new Coin(0);
        Address address = new Address("Viet Nam", "Ho Chi Minh","Q12,Ton Dang");
        customer.setCoin(coin);
        customer.setAddress(address);
        coinReponsitory.save(coin);
        addressRepository.save(address);
    }
    public Customer updateCustomer(Customer customer) {return customerRepository.save(customer);}

    public Customer loadCustomerByEmail(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.loginByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return customer;
    }


    public Customer getCustomerById(int id) {
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

    public void updateCustomer(int idcs, String customerName, String customerPassword,String customerbirthDate,String avatar_url, Address address){
        customerRepository.updateCustomerByID(customerName,customerPassword,customerbirthDate,avatar_url,idcs);
        Address foundadd = addressRepository.findById(address.getId()).get();
        foundadd.setStreet(address.getStreet());
        foundadd.setCity(address.getCity());
        addressRepository.save(address);
    }

}
