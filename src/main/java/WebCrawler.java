import com.google.inject.Inject;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by kuabhay on 7/29/17.
 */
public class WebCrawler implements Crawler{

  private final UrlExtractor urlExtractor;
  private final DataFetcher dataFetcher ;
  private static final String START_URL = "http://www.samsung.com/in/";

  private List<String> urls = null;

  @Inject
  public WebCrawler(UrlExtractor urlExtractor, DataFetcher dataFetcher) {
    this.urlExtractor = urlExtractor;
    this.dataFetcher = dataFetcher;
  }

  public void crawl(String url) {
    String fetchedUrl = dataFetcher.fetchDataFromUrl(url);
    urls.add(fetchedUrl);
  }

  public static void main(String args[]) {
    UrlInfo info = new UrlInfo(START_URL,1);
    UrlHolder.insert(info);

    ExecutorService executor = Executors.newFixedThreadPool(10);
    new DataExecutor(executor).start();
    //new DataExecutor(executor).downLoadWebPage(info);
  }

}
