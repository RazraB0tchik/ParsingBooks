package parsingweb.dev_package.parsingweb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parsingweb.dev_package.parsingweb.dto.AuthorModDTO;
import parsingweb.dev_package.parsingweb.dto.GenreModDTO;
import parsingweb.dev_package.parsingweb.dto.NameModDto;
import parsingweb.dev_package.parsingweb.elements_for_parsing.ParsingChitayGorod;
import parsingweb.dev_package.parsingweb.repositories.BookRepository;


@RestController
@RequestMapping(value = "/getBooks")
public class MainController {

    @Autowired
    ParsingChitayGorod parsingChitayGorod;

    @Autowired
    BookRepository bookRepository;
    @GetMapping(value = "/all")
    public ResponseEntity<?> getBooksAll() {
        parsingChitayGorod.parsingSite();
        return ResponseEntity.ok("ok");
//        return ResponseEntity.ok(bookRepository.findAll());

    }

//    @PostMapping(value = "/nameBook")
//    public ResponseEntity<?> getBooksByName(@RequestBody NameModDto nameModDto){
//        System.out.println(nameModDto.getName());
//        return ResponseEntity.ok(bookRepository.findBookByBookName(nameModDto.getName()));
//    }
//
//    @PostMapping(value = "/author")
//    public ResponseEntity<?> getBooksByAuthor(@RequestBody AuthorModDTO authorModDTO){
//        System.out.println(authorModDTO.getAuthor());
//        return ResponseEntity.ok(bookRepository.findAllByAuthor(authorModDTO.getAuthor()));
//    }
//
//    @PostMapping(value = "/genre")
//    public ResponseEntity<?> getBooksByGenre(@RequestBody GenreModDTO genreModDTO){
//        return ResponseEntity.ok(bookRepository.findBookByGenre(genreModDTO.getGenre()));
//    }
}
