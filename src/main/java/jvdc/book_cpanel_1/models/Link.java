package jvdc.book_cpanel_1.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "link")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "linkid")
    private Integer linkid;

    private String inamgeName;
}
