package parsingweb.dev_package.parsingweb.elements_for_parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import parsingweb.dev_package.parsingweb.entity.Book;
import parsingweb.dev_package.parsingweb.parsing_tools.CreateSaveBookAbstract;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class ParsingBookCards extends CreateSaveBookAbstract {

    private Integer checkСharacteristic(String element) {
        if (Objects.equals(element, "")) {
            return 0;
        } else {
            try {
                return Integer.parseInt(element);
            }
            catch (RuntimeException e){
                return 0;
            }
        }
    }

    private Boolean connectionСheck(String url, String elem) {
        try {
                Jsoup.connect(url + "/catalog/book" + "/" + elem);

                return true;
        }
        catch (Exception e){
            return false;
        }
    }


    @Async
    public void parsingAllPages(String urlSite, String genreLink, String elem){

        book = new Book();
        boolean checkConnection;

            checkConnection=connectionСheck(urlSite, elem);
            if (!checkConnection){
                try {
                    Thread.sleep(10L * 500L);
                    System.out.println("go reload");
                    connectionСheck(urlSite, elem);
                }
                catch (InterruptedException ex) {
                    System.out.println(ex);
                }

            }
            else {
                try {
                    Document cardProduct = Jsoup.connect(urlSite + "/catalog/book" + "/" + elem).get();

                    System.out.println("Im connected!");
                    var cardsOnProduct = cardProduct.select(".grid-container.product.js-analytic-product-page.js__product_card_detail");
                    cardsOnProduct.forEach(elementCard -> {
                        book.setImageBook(elementCard.select(".product-media>.lazyload").attr("data-src")); //images
                        book.setBookName(elementCard.select(".product-title.mb1.js-analytic-product-title").text()); //name
                        book.setAuthor(elementCard.getElementsByAttributeValue("itemprop", "name").attr("content"));
                        book.setDescription(elementCard.getElementsByAttributeValue("itemprop", "description").text()); //description
                        book.setYearStart(checkСharacteristic(elementCard.getElementsByAttributeValue("itemprop", "datePublished").text())); //datePublish
                        book.setBookISBN(elementCard.getElementsByAttributeValue("itemprop", "isbn").text()); //isbn
                        book.setCountPages(checkСharacteristic(elementCard.getElementsByAttributeValue("itemprop", "numberOfPages").text())); //numberOfPages
                        book.setGenre(elementCard.getElementsByAttributeValue("href", genreLink.substring(25)).text()); //genre
                        book.setSite("ЧИТАЙ ГОРОД");
                        book.setUpdateTime(LocalDate.now());
                        saveBookInDataBase(book);
                    });

                } catch (Exception e) {
                    throw new RuntimeException(e);
                    }
                }
            }
    }
