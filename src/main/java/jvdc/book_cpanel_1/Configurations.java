package jvdc.book_cpanel_1;



import jvdc.book_cpanel_1.models.Category;
import jvdc.book_cpanel_1.models.Employee;
import jvdc.book_cpanel_1.models.Product;
import jvdc.book_cpanel_1.models.Role;
import jvdc.book_cpanel_1.repository.CategoryRepository;
import jvdc.book_cpanel_1.repository.EmployeeRepository;
import jvdc.book_cpanel_1.repository.ProductRepository;
import jvdc.book_cpanel_1.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class Configurations {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public Configurations(PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner(
            RoleRepository roleRepository, EmployeeRepository employeeRepository, CategoryRepository categoryRepository, ProductRepository productRepository){
        return  args -> {
            Role role1 = new Role("ADMIN");
            Role role2 = new Role("EDITOR");
            Role role3 = new Role("VIEWER");

            Category category1 = new Category("Truyen Tranh");
            Category category2 = new Category("Sach Giao Khoa");

            roleRepository.save(role1);
            roleRepository.save(role2);
            roleRepository.save(role3);

            categoryRepository.save(category1);categoryRepository.save(category2);

            Product product = new Product("Chi Pheo"
                    ,20.0
                    ,"NXB Kim Dong"
                    ,"Chí Phèo là một truyện ngắn nổi tiếng của nhà văn Nam Cao viết vào tháng 2 năm 1941. Chí Phèo là một tác phẩm xuất sắc, thể hiện nghệ thuật viết truyện độc đáo của Nam Cao, đồng thời là một tấn bi kịch của một người nông dân nghèo bị tha hóa trong xã hội. Chí Phèo cũng là tên nhân vật chính của truyện"
                    ,"Một anh đi thả ống lươn, một buổi sáng tinh sương đã thấy hắn trần truồng và xám ngắt trong một váy đụp để bên một lò gạch bỏ không, anh ta rước lấy và đem về cho một người đàn bà góa mù."
                    ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaFmF1-kYNDfMjtYgELh5XrUC5JNXAAkXu5w&usqp=CAU"
                    ,1
                    ,1
            );
            product.addCategory(category1);

            Product product2 = new Product("Chi Pheo"
                    ,20.0
                    ,"NXB Kim Dong"
                    ,"Chí Phèo là một truyện ngắn nổi tiếng của nhà văn Nam Cao viết vào tháng 2 năm 1941. Chí Phèo là một tác phẩm xuất sắc, thể hiện nghệ thuật viết truyện độc đáo của Nam Cao, đồng thời là một tấn bi kịch của một người nông dân nghèo bị tha hóa trong xã hội. Chí Phèo cũng là tên nhân vật chính của truyện"
                    ,"Một anh đi thả ống lươn, một buổi sáng tinh sương đã thấy hắn trần truồng và xám ngắt trong một váy đụp để bên một lò gạch bỏ không, anh ta rước lấy và đem về cho một người đàn bà góa mù."
                    ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaFmF1-kYNDfMjtYgELh5XrUC5JNXAAkXu5w&usqp=CAU"
                    ,1
                    ,1
            );
            product.addCategory(category2);
            productRepository.save(product);productRepository.save(product2);

            Employee employee = new Employee(true,"emp1","emp1@gmail.com", passwordEncoder.encode("password"), 1234567899);
            employee.addRole(role1);
            employeeRepository.save(employee);

        };
    }

}
