package parsingweb.dev_package.parsingweb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parsingweb.dev_package.parsingweb.repositories.BookRepository;


@RestController
@RequestMapping(value = "/getBooks")
public class MainController {
    @Autowired
    BookRepository bookRepository;


    @GetMapping(value = "/all")
    public ResponseEntity<?> getBooksAll() {
        return ResponseEntity.ok(bookRepository.findAll());
    }
    @GetMapping("/bookName")
    public ResponseEntity<?> getBooksByName(@RequestParam String nameBook){
        return ResponseEntity.ok(bookRepository.findBookByBookName(nameBook));
    }

    @GetMapping("/authorName")
    public ResponseEntity<?> getBooksByAuthor(@RequestParam String authorName){
        return ResponseEntity.ok(bookRepository.findAllByAuthor(authorName));
    }

    @GetMapping("/genre")
    public ResponseEntity<?> getBooksByGenre(@RequestParam String genreBook){
        return ResponseEntity.ok(bookRepository.findBookByGenre(genreBook));
    }
}
