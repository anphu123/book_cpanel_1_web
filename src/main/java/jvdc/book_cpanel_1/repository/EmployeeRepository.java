package jvdc.book_cpanel_1.repository;


import jvdc.book_cpanel_1.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT s FROM Employee s WHERE s.mailEmployee = :mailEmployee")
    public Employee getEmployeesByMailEmployee(@Param("mailEmployee") String mailEmployee);

    @Query("SELECT s FROM Employee s WHERE s.enabled = true ORDER BY s.id")
    List<Employee> selectEmployee();
}
