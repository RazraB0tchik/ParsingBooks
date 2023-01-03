package parsingweb.dev_package.parsingweb.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;

@Data
@Component
@Entity
@Table (name = "books")

public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "book_name")
    String bookName;

    @Column(name = "author")
    String author;

    @Column(name = "description")
    String description;

    @Column(name = "year_start")
    Integer yearStart;

    @Column(name = "imageBook")
    Long imageBook;

    @Column(name = "book_isbn")
    BigInteger bookISBN;

    @Column(name = "count_pages")
    Integer countPages;

    @Column(name = "genre")
    String genre;


    @Column(name = "site")
    String site;

    @Column(name = "date_update")
    Date updateTime;

}
