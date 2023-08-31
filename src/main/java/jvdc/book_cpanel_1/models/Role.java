package jvdc.book_cpanel_1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    public Integer getId() {
        return id;
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(Integer id) {this.id = id;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
