package parsingweb.dev_package.parsingweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import parsingweb.dev_package.parsingweb.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
//    Book findBookById(int id);
    Book findBookByBookName(String name);

    List<Book> findAllByAuthor(String author);

    List<Book> findBookByGenre(String genre);
}
