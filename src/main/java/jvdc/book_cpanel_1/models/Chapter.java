package jvdc.book_cpanel_1.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private Integer id;

    private String Name;
    private Integer mangaid;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "linkid", referencedColumnName = "id")
    private List<Link> linkList;


}
