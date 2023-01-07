package parsingweb.dev_package.parsingweb.service.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parsingweb.dev_package.parsingweb.tools.ParsingInterface;

import java.util.ArrayList;

@Service
public class ParserChitayGorod implements ParsingInterface {

    private final String urlSite = "https://m.chitai-gorod.ru";
    private final ArrayList<String> linksToBookGenres = new ArrayList<>();
    private String defUrlPage;


    @Autowired
    ParserBookCards parserBookCards;
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

                            parserBookCards.parsingAllPages(urlSite, genreLink, elem.attr("data-chg-id"));

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
