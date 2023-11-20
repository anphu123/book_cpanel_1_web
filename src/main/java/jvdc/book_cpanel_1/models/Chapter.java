package jvdc.book_cpanel_1.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Getter
@Setter
@Table(name = "chapter")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Name;
    private int mangaid;
    private boolean paytoview;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "linkid", referencedColumnName = "id")
    private List<Link> linkList;

}
