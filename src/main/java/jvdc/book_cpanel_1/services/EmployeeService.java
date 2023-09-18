package jvdc.book_cpanel_1.services;
import jvdc.book_cpanel_1.models.Employee;
import jvdc.book_cpanel_1.repository.EmployeeRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> listAll() {
        return employeeRepository.selectEmployee();
    }

    public Employee save1(Employee employee) {
        return employeeRepository.save(employee);
    }
    public Employee save2(int id) {
        Employee employee= get(id);
        employee.setEnabled(false);
        return employeeRepository.save(employee);
    }

    public Employee get(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else
            try {
                throw new Exception("Username not found " + id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    public void delete(int id){
        boolean exists = employeeRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("emp with id " + id +" doesn't exist");
        }
        employeeRepository.deleteById(id);
    }
}
