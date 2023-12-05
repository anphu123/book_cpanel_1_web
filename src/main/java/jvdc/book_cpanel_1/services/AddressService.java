package jvdc.book_cpanel_1.services;

import jvdc.book_cpanel_1.models.Address;
import jvdc.book_cpanel_1.models.Coin;
import jvdc.book_cpanel_1.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
