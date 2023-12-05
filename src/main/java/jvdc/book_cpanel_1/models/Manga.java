package jvdc.book_cpanel_1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@ToString
@Table(name = "products")
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mangaid", referencedColumnName = "id")
    private List<MangaComment> mangaCommentList;
    public void addComment(MangaComment mangaComment){this.mangaCommentList.add(mangaComment);}
    public void removeComment(MangaComment mangaComment){
        this.mangaCommentList.remove(mangaComment);
    }
}
