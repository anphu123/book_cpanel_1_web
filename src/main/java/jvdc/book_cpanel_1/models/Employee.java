package jvdc.book_cpanel_1.models;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 3, max = 25)
    private String nameEmployee;

    @Email
    @NotNull
    @Size(min = 3, max = 25)
    private String mailEmployee;

    @NotNull
    @Size(min = 3, max = 33)
    private String password;


    @NotNull
    @Min(1000000000)
    private Integer phoneEmployee;

    private Integer createrId;


    /*cascade de tao user moi cung role moi*/
    /*fecth de load emp cung role cung luc*/
//    (cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "emp_role",
            joinColumns = @JoinColumn(name = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @NotNull
    private boolean enabled;

    public Employee(String nameEmployee, String mailEmployee, String password, Integer phoneEmployee) {
        this.nameEmployee = nameEmployee;
        this.mailEmployee = mailEmployee;
        this.password = password;
        this.phoneEmployee = phoneEmployee;
    }

    public Employee(boolean enabled,String nameEmployee, String mailEmployee, String password, Integer phoneEmployee) {
        this.enabled = enabled;
        this.nameEmployee = nameEmployee;
        this.mailEmployee = mailEmployee;
        this.password = password;
        this.phoneEmployee = phoneEmployee;


    }

    public Employee(String nameEmployee, String mailEmployee, String password, Integer phoneEmployee, Integer createrId, boolean enabled) {
        this.nameEmployee = nameEmployee;
        this.mailEmployee = mailEmployee;
        this.password = password;
        this.phoneEmployee = phoneEmployee;
        this.createrId = createrId;
        this.enabled = enabled;
    }

    public Employee(String nameEmployee, String mailEmployee, String password, Integer phoneEmployee, Integer createrId, Set<Role> roles, boolean enabled) {
        this.nameEmployee = nameEmployee;
        this.mailEmployee = mailEmployee;
        this.password = password;
        this.phoneEmployee = phoneEmployee;
        this.createrId = createrId;
        this.roles = roles;
        this.enabled = enabled;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }
    public void removeRole(Role role){
        this.roles.remove(role);
    }




}
