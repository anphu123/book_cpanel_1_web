//package jvdc.book_cpanel_1;
//
//
//
//import jvdc.book_cpanel_1.models.Category;
//import jvdc.book_cpanel_1.models.Employee;
//import jvdc.book_cpanel_1.models.Product;
//import jvdc.book_cpanel_1.models.Role;
//import jvdc.book_cpanel_1.repository.CategoryRepository;
//import jvdc.book_cpanel_1.repository.EmployeeRepository;
//import jvdc.book_cpanel_1.repository.ProductRepository;
//import jvdc.book_cpanel_1.repository.RoleRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@Configuration
//public class Configurations {
//    private final PasswordEncoder passwordEncoder;
//    private final RoleRepository roleRepository;
//
//    public Configurations(PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
//        this.passwordEncoder = passwordEncoder;
//        this.roleRepository = roleRepository;
//    }
//
//    @Bean
//    CommandLineRunner commandLineRunner(
//            RoleRepository roleRepository, EmployeeRepository employeeRepository, CategoryRepository categoryRepository, ProductRepository productRepository){
//        return  args -> {
//            Role role1 = new Role("ADMIN");
//            Role role2 = new Role("EDITOR");
//            Role role3 = new Role("VIEWER");
//
//            Category category1 = new Category("Truyen Tranh");
//            Category category2 = new Category("Sach Giao Khoa");
//
//            roleRepository.save(role1);
//            roleRepository.save(role2);
//            roleRepository.save(role3);
//
//            categoryRepository.save(category1);categoryRepository.save(category2);
//
//
//
//            Employee employee = new Employee(true,"adm","adm@gmail.com", passwordEncoder.encode("password"), 1234567809);
//            employee.setCreaterId(-1);
//            employee.addRole(role1);
//            Employee employee2 = new Employee(true,"editor","editor@gmail.com", passwordEncoder.encode("password"), 1234567879);
//            employee2.setCreaterId(1);
//            employee2.addRole(role2);
//            Employee employee3 = new Employee(true,"emp1","emp1@gmail.com", passwordEncoder.encode("password"), 1234567899);
//            employee3.setCreaterId(1);
//            employee3.addRole(role3);
//            employeeRepository.save(employee);
//            employeeRepository.save(employee2);
//            employeeRepository.save(employee3);
//
//
//
//        };
//    }
//
//}
