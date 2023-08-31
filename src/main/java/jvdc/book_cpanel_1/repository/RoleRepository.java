package jvdc.book_cpanel_1.repository;


import jvdc.book_cpanel_1.models.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Qualifier("role")
@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {
}
