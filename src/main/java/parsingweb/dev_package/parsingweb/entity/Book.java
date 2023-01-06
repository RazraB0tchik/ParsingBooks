package parsingweb.dev_package.parsingweb.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Data
@Component
@Entity
@Table (name = "books")

public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "book_name")
    String bookName;

    @Column(name = "author")
    String author;

    @Column(name = "description")
    String description;

    @Column(name = "year_start")
    Integer yearStart;

    @Column(name = "imageBook")
    String imageBook;

    @Column(name = "book_isbn")
    String bookISBN;

    @Column(name = "count_pages")
    Integer countPages;

    @Column(name = "genre")
    String genre;


    @Column(name = "site")
    String site;

    @Column(name = "date_update")
    LocalDate updateTime;


    public Book(String bookName, String author, String description, Integer yearStart, String imageBook, String bookISBN, Integer countPages, String genre, String site, LocalDate updateTime) {
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.yearStart = yearStart;
        this.imageBook = imageBook;
        this.bookISBN = bookISBN;
        this.countPages = countPages;
        this.genre = genre;
        this.site = site;
        this.updateTime = updateTime;
    }

    public Book() {
    }
}
