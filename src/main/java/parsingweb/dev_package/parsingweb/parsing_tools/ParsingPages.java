package parsingweb.dev_package.parsingweb.parsing_tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class ParsingPages {

    public void parsingAllPages(String urlSite, Integer countPage){
        String defUrlPage = urlSite + "?page=";
        try {
            int page;
            for (page=1; page <= countPage; page++) {
                Document booksOnPage = Jsoup.connect(defUrlPage+String.valueOf(page))
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com")
                        .get();
                var allBooksOnPage = booksOnPage.getElementsByClass("product-card");
                allBooksOnPage.forEach(elem -> {
                    System.out.println(elem.attr("data-chg-id"));
                });

            }

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
