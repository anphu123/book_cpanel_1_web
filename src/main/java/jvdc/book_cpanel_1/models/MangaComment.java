package jvdc.book_cpanel_1.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@ToString
@Setter
@Getter
@Table(name = "mangacomment")
public class MangaComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String body;

    @ManyToOne
    private Customer user;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;
    private Date updated_at;

    private int mangaid;


    public MangaComment(String body) {
        this.body = body;
    }

    public MangaComment(String body, Customer user, Date created_at, Date updated_at) {
        this.body = body;
        this.user = user;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MangaComment(String body, int mangaid) {
        this.body = body;
        this.mangaid = mangaid;
    }
}
