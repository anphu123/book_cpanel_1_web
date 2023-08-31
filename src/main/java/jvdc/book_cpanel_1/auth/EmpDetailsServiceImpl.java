package jvdc.book_cpanel_1.auth;

import jvdc.book_cpanel_1.models.Employee;
import jvdc.book_cpanel_1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmpDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.getEmployeesByMailEmployee(email);
        if (employee == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(employee);
    }
}
