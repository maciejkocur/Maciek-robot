import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmpikBookExtractor implements BookExtractor {

    public Iterable<BookInfo> extract() throws IOException {
        Connection connect = Jsoup.connect("http://www.empik.com/ebooki/promocje/");
        Set<BookInfo> books = new HashSet<BookInfo>();


        Document document = connect.get();
        Elements contentPacket = document.getElementsByAttributeValue("class", "more_lnk");

        List<String> sectionUrls = new ArrayList<String>();
        for (Element element : contentPacket) {
            sectionUrls.add("http://www.empik.com" + element.attr("href"));
        }

        for (String url : sectionUrls) {
            books.addAll(getBooksFromSection(connect.url(url).get()));
        }

        return books;
    }

    private Set<BookInfo> getBooksFromSection(Document document) throws IOException {
        Set<BookInfo> books = new HashSet<BookInfo>();
        List<String> booksUrls = new ArrayList<String>();
        Elements elements = document.getElementsByAttributeValue("class", "productBox-450Pic");
        for (Element element : elements) {
            String link = element.attr("href");
            if (link.equals("")) {
                continue;
            }
            booksUrls.add("http://www.empik.com" + link);
        }
        for (String url : booksUrls) {
            books.add(getBook(Jsoup.connect(url).get()));
        }
        return books;
    }

    private BookInfo getBook(Document aClass) {
        String text = aClass.getElementsByAttributeValue("class", "productMainTitle").get(0).text();
        System.out.println(text);
        return new BookInfo(text);
    }
}

