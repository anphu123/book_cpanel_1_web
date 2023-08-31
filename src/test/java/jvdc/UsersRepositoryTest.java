package jvdc;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UsersRepositoryTest {

//    @Autowired
//    private EmployeeRepository repo;

    @Autowired
    private TestEntityManager testEntityManager;
//    @Test
//    public void test1(){
//    }
//    @Test
//    public void test2(){
//        Role role1 = new Role(3,"VIEWER");
//        Role role2 = new Role(2,"EDITOR");
//        Role role3 = new Role(1,"ADMIN");
//        testEntityManager.persist(role1);
//        testEntityManager.persist(role2);
//        testEntityManager.persist(role3);
//    }
//
//    @Test
//    public void test3CreateEmpWithOneRole(){
//        Role roleviwer = testEntityManager.find(Role.class,1);
//        Role roleeditor = testEntityManager.find(Role.class,2);
//        Role roleadmin = testEntityManager.find(Role.class,3);
//        Employee employee = new Employee(true,"emp1","emp1@gamil.com","5f4dcc3b5aa765d61d8327deb882cf99",1234567899);
//        employee.addRole(roleviwer);
//        repo.save(employee);
//    }
//
//    @Test
//    public void testCreateEmpWithTwoRoles(){
//        Role roleEditor = testEntityManager.find(Role.class, 1);
//        Role roleAdmin = testEntityManager.find(Role.class, 2);
//        Employee employee = new Employee(true,"emp3","emp3@gamil.com","password",1234567899);
//        employee.addRole(roleEditor);
//        employee.addRole(roleAdmin);
//
//        repo.save(employee);
//    }
//
//    @Test
//    public void testAssignRoleToExistingEmp(){
//        Employee employee = repo.findById(1).get();
//        Role roleEditor = testEntityManager.find(Role.class, 1);
//        employee.addRole(roleEditor);
//    }
//
//    @Test
//    public void testRemoveRoleFromExistingEmp(){
//        Employee employee = repo.findById(1).get();
//        Role role = new Role(1);
//        employee.removeRole(role);
//
//
//    }
//    @Test
//    public void tesetCreateNewUserWithNewRole(){
//        Role viewrole = new Role("ADMIN");
//        Employee employee = new Employee(true,"emp4","emp4@gamil.com","password",1234567899);
//        employee.addRole(viewrole);
//        repo.save(employee);
//    }

//
//
//    @Test
//    public void testFindByEmail() {
//        String email = "emp3@gmail.com";
//        Employee employee = repo.getEmployeesByMailEmployee(email);
//        System.out.println(employee.getNameEmployee());
//
//    }

}
