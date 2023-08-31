package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.models.Employee;
import jvdc.book_cpanel_1.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employee")
public class EmpControllerApi {

    @Autowired
    private  EmployeeService employeeService;
//    http://localhost:8080/api/v1/employee
    @GetMapping
    public List<Employee> getEmployees(){
        List<Employee> employeeList = employeeService.listAll();
        return employeeList;
    }

//    http://localhost:8080/api/v1/employee/get-one/{id}
    @GetMapping(value = "/get-one/{id}")
        public Employee selectSingleEmp(@PathVariable(name = "id")int id){
        Employee employee = employeeService.get(id);
        return employee;
    }

//    http://localhost:8080/api/v1/employee/delete/{id}
    @DeleteMapping(value = "delete/{id}")
    public void deleteEmp(@PathVariable(name = "id")int id){
        employeeService.delete(id);
    }

//    http://localhost:8080/api/v1/employee/create-new
    @PostMapping(value = "/create-new")
    public void registerNewEmp(@RequestBody Employee employee){
        System.out.println(employee);
        employeeService.save1(employee);
    }

}
/*
*  {
        "id": "74",
        "nameEmployee": "viewer9",
        "mailEmployee": "viewer7@gmail.com",
        "password": "81dc9bdb52d04dc20036dbd8313ed055",
        "phoneEmployee": 1234567899,
        "createrId": 3,

        "enabled": true
    }
* */