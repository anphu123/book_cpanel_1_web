package jvdc.book_cpanel_1.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ImageData")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String bookname;
    private String type;

    @Column(name = "chapterid")
    private int chapterID;

    @Column(name = "linkid")
    private int linkID;

    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;


}
