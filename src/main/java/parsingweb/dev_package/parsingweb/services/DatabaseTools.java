package parsingweb.dev_package.parsingweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import parsingweb.dev_package.parsingweb.entity.Book;
import parsingweb.dev_package.parsingweb.repositories.BookRepository;

@Service
public class DatabaseTools {

    @Autowired
    BookRepository bookRepository;

    public boolean checkBookInDatabase(String name){
        if (bookRepository.findBookByBookName(name)!=null){
            return false;
        }
        else return true;
    }

    @Async
    public void saveBookInDataBase(Book book){
       if (checkBookInDatabase(book.getBookName())){
           bookRepository.save(book);
       }
    }

}
