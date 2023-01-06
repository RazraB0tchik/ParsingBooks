package parsingweb.dev_package.parsingweb.elements_for_parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import parsingweb.dev_package.parsingweb.entity.Book;
import parsingweb.dev_package.parsingweb.parsing_tools.ParsingAbtractClass;
import parsingweb.dev_package.parsingweb.services.DatabaseTools;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class ParsingChitayGorod extends ParsingAbtractClass {


    @Autowired
    DatabaseTools databaseTools;

    String urlSite = "https://m.chitai-gorod.ru";
    ArrayList<String> linksToBookGenres = new ArrayList<>();
    String defUrlPage;

    public Integer checkСharacteristic(String element){
        if (Objects.equals(element, "")){
            return 0;
        }
        else{
            return Integer.parseInt(element);
        }
    }

    @Override
    public void parsingSite() {

        try {
            Document bookPage = Jsoup.connect(urlSite+"/catalog/books") //переход на главную страницу книг
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            var info = bookPage.getElementsByClass("pt4");
            info.forEach(element -> {
                linksToBookGenres.add(urlSite + element.child(2).attr("href"));
            });
            linksToBookGenres.forEach(genreLink -> {
//                try {
//                    Document genrePage = Jsoup.connect(genreLink)
//                            .userAgent("Chrome/4.0.249.0 Safari/532.5")
//                            .referrer("http://www.google.com")
//                            .get();

//                    try {
//                        Document lastPage = Jsoup.connect(urlSite + pageOnGenres.get(1).select("a").attr("href"))
//                                .userAgent("Chrome/4.0.249.0 Safari/532.5")
//                                .referrer("http://www.google.com")
//                                .get();
//                        var lastPageElement = lastPage.getElementsByClass("current");
//


                        defUrlPage = genreLink + "?page=";

                        try {
                            int page = 1;
                            boolean stopPars = false;
//                            for (page=1; page <= Integer.parseInt(lastPageElement.select("li").text()); page++) {
//                                System.out.println(pageOnGenres.text());

                                while (!stopPars){
                                    Document booksOnPage = Jsoup.connect(defUrlPage + page) //генерируем ссылку для каждого жанра на страницу
                                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                                        .referrer("http://www.google.com")
                                        .get();

                                var pageOnGenres = booksOnPage.select(".pagination.text-center").get(0).getElementsByClass("current");
                                if (Integer.parseInt(pageOnGenres.text())!=page){
                                    stopPars=true;
                                }

                                var allBooksOnPage = booksOnPage.getElementsByClass("product-card");
                                allBooksOnPage.forEach(elem -> {
                                    try {
                                        Document cardProduct = Jsoup.connect(urlSite+"/catalog/book" + "/" + elem.attr("data-chg-id")) //переход на страницу карточки
                                                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                                                .referrer("http://www.google.com")
                                                .get();

//
                                        var cardsOnProduct = cardProduct.select( ".grid-container.product.js-analytic-product-page.js__product_card_detail");
                                        cardsOnProduct.forEach(elementCard -> {
                                            setAbsImageBook(elementCard.select(".product-media>.lazyload").attr("data-src")); //images
                                            setAbsBookName(elementCard.select(".product-title.mb1.js-analytic-product-title").text()); //name
                                            setAbsAuthor(elementCard.getElementsByAttributeValue("itemprop", "name").attr("content"));
                                            setAbsDescription(elementCard.getElementsByAttributeValue("itemprop", "description").text()); //description
                                            setAbsYearStart(checkСharacteristic(elementCard.getElementsByAttributeValue("itemprop", "datePublished").text())); //datePublish
                                            setAbsBookISBN(elementCard.getElementsByAttributeValue("itemprop", "isbn").text()); //isbn
                                            setAbsCountPages(checkСharacteristic(elementCard.getElementsByAttributeValue("itemprop", "numberOfPages").text())); //numberOfPages
                                            setAbsGenre(elementCard.getElementsByAttributeValue("href", genreLink.substring(25)).text()); //genre
//                                            System.out.println(elementCard.getElementsByAttributeValue("itemprop", "description").text()); //description
//                                            System.out.println(elementCard.getElementsByAttributeValue("itemprop", "datePublished").text()); //datePublish
//                                            System.out.println(elementCard.getElementsByAttributeValue("itemprop", "isbn").text()); //isbn
//                                            System.out.println(elementCard.getElementsByAttributeValue("itemprop", "numberOfPages").text()); //numberOfPages
//                                            System.out.println( genreLink); //genre
//                                            System.out.println(elementCard.getElementsByAttributeValue("href", genreLink.substring(25)).text()); //genre


                                            setAbsSite("ЧИТАЙ ГОРОД");
                                            Book book = new Book(AbsBookName, AbsAuthor, AbsDescription, AbsYearStart, AbsImageBook, AbsBookISBN, AbsCountPages, AbsGenre, AbsSite, LocalDate.now());
                                            databaseTools.saveBookInDataBase(book);
                                        });
                                    }
                                    catch (Exception e){
                                        throw new RuntimeException(e);
                                    }
                                });
                                    page++;
                            }

                        }
                        catch (Exception e){
                            throw new RuntimeException(e);
                        }
//                    }
//                    catch (Exception e){
//                        throw new RuntimeException(e);
//                    }
//                }
//                catch (Exception e){
//                    throw new RuntimeException(e);
//                }

            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
