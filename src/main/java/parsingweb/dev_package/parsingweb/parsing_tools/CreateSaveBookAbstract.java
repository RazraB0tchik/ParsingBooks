package parsingweb.dev_package.parsingweb.parsing_tools;

import lombok.Data;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import parsingweb.dev_package.parsingweb.entity.Book;
import parsingweb.dev_package.parsingweb.repositories.BookRepository;

@Data
public abstract class CreateSaveBookAbstract {

    @Autowired
    BookRepository bookRepository;

    protected Book book;

    public boolean checkBookInDatabase(String name){
        if (bookRepository.findBookByBookName(name)!=null){
            return false;
        }
        else return true;
    }

    @Synchronized
    public void saveBookInDataBase(Book book){
        if (checkBookInDatabase(book.getBookName())){
            bookRepository.save(book);
        }
    }
}
