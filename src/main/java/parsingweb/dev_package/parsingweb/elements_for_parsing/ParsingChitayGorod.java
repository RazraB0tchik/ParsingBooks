package parsingweb.dev_package.parsingweb.elements_for_parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import parsingweb.dev_package.parsingweb.parsing_tools.ParsingInterface;

import java.util.ArrayList;

@Component
public class ParsingChitayGorod implements ParsingInterface {

    String urlSite = "https://m.chitai-gorod.ru";
    ArrayList<String> linksToBookGenres = new ArrayList<>();
    String defUrlPage;


    @Autowired
    ParsingBookCards parsingBookCards;
    @Override
    public void parsingSite() {

        try {
            Document bookPage = Jsoup.connect(urlSite+"/catalog/books").get();
            var info = bookPage.getElementsByClass("pt4");
            info.forEach(element -> {
                linksToBookGenres.add(urlSite + element.child(2).attr("href"));
            });
            linksToBookGenres.forEach(genreLink -> {

                defUrlPage = genreLink + "?page=";

                try {
                    int page = 1;
                    boolean stopPars = false;

                    while (!stopPars) {
                        Document booksOnPage = Jsoup.connect(defUrlPage + page).get();
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
