package parsingweb.dev_package.parsingweb.controllers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import parsingweb.dev_package.parsingweb.parsing_tools.ParsingPages;

import java.util.ArrayList;


@RestController
public class MainController {

    @Autowired
    ParsingPages parsingPages;

    @GetMapping(value = "/getInfo")
    public String getAnswer() {
        String urlSite = "https://m.chitai-gorod.ru";
        ArrayList<String> linksToBookGenres = new ArrayList();


        try {
            Document bookPage = Jsoup.connect(urlSite+"/catalog/books")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            var info = bookPage.getElementsByClass("pt4");
            info.forEach(element -> {
                linksToBookGenres.add(urlSite + element.child(2).attr("href"));
            });
            linksToBookGenres.forEach(genreLink -> {
                try {
                    Document genrePage = Jsoup.connect(genreLink)
                            .userAgent("Chrome/4.0.249.0 Safari/532.5")
                            .referrer("http://www.google.com")
                            .get();
                    var pageOnGenres = genrePage.getElementsByClass("pagination");

                    try {
                        Document lastPage = Jsoup.connect(urlSite + pageOnGenres.get(1).select("a").attr("href"))
                                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                                .referrer("http://www.google.com")
                                .get();
                        var lastPageElement = lastPage.getElementsByClass("current");
                        System.out.println(lastPageElement.select("li").text());

                        parsingPages.parsingAllPages(genreLink, Integer.valueOf(lastPageElement.select("li").text()));
                    }
                    catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
                catch (Exception e){
                    throw new RuntimeException(e);
                }

            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "ok, im parsed";
    }
}
