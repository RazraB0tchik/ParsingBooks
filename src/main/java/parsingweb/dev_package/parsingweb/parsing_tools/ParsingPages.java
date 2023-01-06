//package parsingweb.dev_package.parsingweb.parsing_tools;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ParsingPages {
//
//    public void parsingAllPages(String urlSite, Integer countPage, String elementPageLink){
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
//    }
//
//}
