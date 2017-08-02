
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by kuabhay on 7/30/17.
 */
public class DataExecutor {

  private final ExecutorService executor;
  public static final int THRESHOLD_DEPTH = 5;


  DataExecutor(ExecutorService executor) {
    this.executor = executor;
  }

  void start () {
    while(true && UrlHolder.size() < 10000) {
     final UrlInfo url = UrlHolder.next();
      if(url != null && url.getDepth() < THRESHOLD_DEPTH) {

        Callable<List<UrlInfo>> task = new Callable<List<UrlInfo>>() {
          public List<UrlInfo> call() throws Exception {
            List<UrlInfo> urlInfoList = downLoadWebPage(url);
            return urlInfoList;
          }
        };

        Future<List<UrlInfo>> future = executor.submit(task);

        try{
          List<UrlInfo> urlInfoList = future.get();
          for (UrlInfo urlInfo : urlInfoList) {
            System.out.println("proccessing url "+ urlInfo.getUrlName() + " " + urlInfo.getDepth());
            if(urlInfo.getDepth() < THRESHOLD_DEPTH)
            UrlHolder.insert(urlInfo);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (ExecutionException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public List<UrlInfo> downLoadWebPage(UrlInfo url) {
    assert (url != null);
    int depth = url.getDepth();
    List<UrlInfo> urlInfoList = new ArrayList<UrlInfo>();
    try {
      Document document = Jsoup.connect(url.getUrlName()).get();
      Elements links = document.select("a[href]");

      for(Element link : links) {
        String urlName = link.attr("abs:href");
        urlInfoList.add(new UrlInfo(urlName,depth+1));
      }

    }
    catch (Exception e) {
      System.out.println("exception e " + e.getMessage());

    }
    return urlInfoList;
  }
}
