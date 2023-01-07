package parsingweb.dev_package.parsingweb.parsing_tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import parsingweb.dev_package.parsingweb.entity.Book;
import parsingweb.dev_package.parsingweb.services.DatabaseTools;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class ParsingBookCards {

    @Autowired
    DatabaseTools databaseTools;

    private Integer checkСharacteristic(String element) {
        if (Objects.equals(element, "")) {
            return 0;
        } else {
            return Integer.parseInt(element);
        }
    }

    public Boolean connectionСheck(String url, String elem) {
        try {
                Jsoup.connect(url + "/catalog/book" + "/" + elem);//переход на страницу карточки

                return true;
        }
        catch (Exception e){
            return false;
        }
    }


    @Async
    public void parsingAllPages(String urlSite, String genreLink, String elem) {

        Book book = new Book();
        boolean checkConnection;
//        System.out.println("1");
//        System.out.println(urlSite+"/catalog/book" + "/" + elem);
//
//        try {
//            int page = 1;
//            boolean stopPars = false;
////                            for (page=1; page <= Integer.parseInt(lastPageElement.select("li").text()); page++) {
////                                System.out.println(pageOnGenres.text());
//
//            while (!stopPars){
//                Document booksOnPage = Jsoup.connect(defUrlPage + page) //генерируем ссылку для каждого жанра на страницу
//                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
//                        .referrer("http://www.google.com")
//                        .get();
//                var pageOnGenres = booksOnPage.select(".pagination.text-center").get(0).getElementsByClass("current");
//                if (Integer.parseInt(pageOnGenres.text())!=page){
//                    stopPars=true;
//                }
//                var allBooksOnPage = booksOnPage.getElementsByClass("product-card");
//                allBooksOnPage.forEach(elem -> {
//            Document cardProduct = Jsoup.connect(urlSite + "/catalog/book" + "/" + elem) //переход на страницу карточки
//                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
//                    .referrer("http://www.google.com")
//                    .get();

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
//            Document cardProduct = connectionСheck(urlSite, elem);
//
                try {
                    Document cardProduct = Jsoup.connect(urlSite + "/catalog/book" + "/" + elem) //переход на страницу карточки
                            .userAgent("Chrome/4.0.249.0 Safari/532.5")
                            .referrer("http://www.google.com")
                            .get();
//            cardPage = cardProduct;

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
//                                            System.out.println(elementCard.getElementsByAttributeValue("itemprop", "description").text()); //description
//                                            System.out.println(elementCard.getElementsByAttributeValue("itemprop", "datePublished").text()); //datePublish
//                                            System.out.println(elementCard.getElementsByAttributeValue("itemprop", "isbn").text()); //isbn
//                                            System.out.println(elementCard.getElementsByAttributeValue("itemprop", "numberOfPages").text()); //numberOfPages
//                                            System.out.println( genreLink); //genre
//                                            System.out.println(elementCard.getElementsByAttributeValue("href", genreLink.substring(25)).text()); //genre


                        book.setSite("ЧИТАЙ ГОРОД");
                        book.setUpdateTime(LocalDate.now());
//                            Book book = new Book(AbsBookName, AbsAuthor, AbsDescription, AbsYearStart, AbsImageBook, AbsBookISBN, AbsCountPages, AbsGenre, AbsSite, LocalDate.now());
                        databaseTools.saveBookInDataBase(book);
                    });
//                });
//                page++;

                } catch (Exception e) {
//                    try {
//                        Thread.sleep(10L * 500L);
//                    } catch (InterruptedException ex) {
                        System.out.println(e.getMessage());
                    }
//                    try {
////                        Thread.sleep(5L * 500L);
//                    } catch (InterruptedException ex) {
//                        throw new RuntimeException(ex);
//                    }
                }
            }
    }
//        catch (Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//
//}
















//        String defUrlPage = urlSite + "?page=";
//        try {
//            int page;
//            for (page=1; page <= countPage; page++) {
//                Document booksOnPage = Jsoup.connect(defUrlPage+String.valueOf(page))
//                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
//                        .referrer("http://www.google.com")
//                        .get();
//                var allBooksOnPage = booksOnPage.getElementsByClass("product-card");
//                allBooksOnPage.forEach(elem -> {
//                    try {
//                        Document cardProduct = Jsoup.connect(elementPageLink + "/" + elem.attr("data-chg-id"))
//                                .userAgent("Chrome/4.0.249.0 Safari/532.5")
//                                .referrer("http://www.google.com")
//                                .get();
//
////                        var cardsOnProduct = cardProduct.getElementsByClass("js__slider_viewport");
//                        var cardsOnProduct = cardProduct.select( ".grid-container.product.js-analytic-product-page.js__product_card_detail");
//                        cardsOnProduct.forEach(elementCard -> {
//
////                            System.out.println(elementCard);
//                            System.out.println(elementCard.select(".product-media>.lazyload").attr("data-src")); //images
//                            System.out.println(elementCard.select(".product-title.mb1.js-analytic-product-title").text()); //name
//                            System.out.println(elementCard.select(".item.color_secondary>.bb").text()); //author
//                            System.out.println(elementCard.getElementsByAttributeValue("itemprop", "description").text()); //description
//                            System.out.println(elementCard.getElementsByAttributeValue("itemprop", "description").text()); //description
//                            System.out.println(elementCard.getElementsByAttributeValue("itemprop", "datePublished").text()); //datePublish
//                            System.out.println(elementCard.getElementsByAttributeValue("itemprop", "isbn").text()); //isbn
//                            System.out.println(elementCard.getElementsByAttributeValue("itemprop", "numberOfPages").text()); //numberOfPages
//                        });
//                    }
//                    catch (Exception e){
//                        throw new RuntimeException(e);
//                    }
//                });
//
//            }
//
//        }
//        catch (Exception e){
//            throw new RuntimeException(e);
//        }
