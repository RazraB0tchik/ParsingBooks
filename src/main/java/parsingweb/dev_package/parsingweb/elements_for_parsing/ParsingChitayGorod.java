package parsingweb.dev_package.parsingweb.elements_for_parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import parsingweb.dev_package.parsingweb.entity.Book;
import parsingweb.dev_package.parsingweb.parsing_tools.ParsingAbtractClass;
import parsingweb.dev_package.parsingweb.parsing_tools.ParsingBookCards;
import parsingweb.dev_package.parsingweb.services.DatabaseTools;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class ParsingChitayGorod extends ParsingAbtractClass {

    String urlSite = "https://m.chitai-gorod.ru";
    ArrayList<String> linksToBookGenres = new ArrayList<>();
    String defUrlPage;


    @Autowired
    ParsingBookCards parsingBookCards;
    @Override
    public void parsingSite() {

        try {
            Document bookPage = Jsoup.connect(urlSite+"/catalog/books") //переход на главную страницу книг
//                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
//                    .referrer("http://www.google.com")
                    .get();
            var info = bookPage.getElementsByClass("pt4");
            System.out.println("sout@@@@!");
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

//                System.out.println(g);

                defUrlPage = genreLink + "?page=";

                try {
                    int page = 1;
                    boolean stopPars = false;
//                            for (page=1; page <= Integer.parseInt(lastPageElement.select("li").text()); page++) {
//                                System.out.println(pageOnGenres.text());

                    while (!stopPars) {
                        Document booksOnPage = Jsoup.connect(defUrlPage + page) //генерируем ссылку для каждого жанра на страницу
//                                .userAgent("Chrome/4.0.249.0 Safari/532.5")
//                                .referrer("http://www.google.com")
                                .get();
                        var pageOnGenres = booksOnPage.select(".pagination.text-center").get(0).getElementsByClass("current");
                        if (Integer.parseInt(pageOnGenres.text()) != page) {
                            stopPars = true;
                        }
                        var allBooksOnPage = booksOnPage.getElementsByClass("product-card");
                        allBooksOnPage.forEach(elem -> {

                            parsingBookCards.parsingAllPages(urlSite, genreLink, elem.attr("data-chg-id"));

                        });
                        page++;

                    }
                }
                catch (Exception e){
                    throw new RuntimeException(e);
                    }


            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
