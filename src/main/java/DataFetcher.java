import com.google.inject.Singleton;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by kuabhay on 7/29/17.
 */

@Singleton
public class DataFetcher {
    String fetchDataFromUrl(String url) {
      String absHref =null;
      try {

        Document document = Jsoup.connect(url).get();
        Element link = document.select("a").first();
        absHref = link.attr("abs:href");
        return absHref;

      } catch (Exception e) {
      }

      return absHref;
    }
}
